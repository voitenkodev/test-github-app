package com.voitenko.testgithubapp.githubrepositories.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzle.pizza.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GithubRepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<GithubRepositoriesState> = MutableStateFlow(GithubRepositoriesState())
    val state: StateFlow<GithubRepositoriesState> = _state.asStateFlow()

    private var searchJob: Job? = null

    fun setQuery(value: String) {
        _state.update { it.copy(query = value) }
        debounceSearch(value)
    }

    private fun debounceSearch(query: String) {
        viewModelScope.launch {
            searchJob?.cancel()

            if (query.length < MINIMAL_QUERY_LENGTH) {
                return@launch
            }

            searchJob = launch searchLaunch@{

                delay(DEBOUNCE_TIME_MS)

                fetchList(query)
                    .launchIn(this)
            }
        }
    }

    private fun fetchList(query: String) = getRepositoriesUseCase
        .invoke(query = query)
        .onStart {
            _state.update { it.copy(loading = true, error = null) }
        }.onEach { response ->
            _state.update {
                it.copy(
                    repositories = response.toPersistentList(),
                    loading = false,
                    error = null
                )
            }
        }.catch {
            _state.update { it.copy(loading = false, error = it.error) }
        }

    fun clearErrors() {
        _state.update { it.copy(error = null) }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
        searchJob = null
    }

    companion object {
        private const val DEBOUNCE_TIME_MS = 700L
        private const val MINIMAL_QUERY_LENGTH = 3
    }
}