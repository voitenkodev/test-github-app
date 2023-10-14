package com.voitenko.testgithubapp.githubrepositories.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voitenko.testgithubapp.GetRepositoriesUseCase
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
        _state.update {
            it.copy(query = value)
        }

        if (value.length < QUERY_LENGTH_VALIDATION && searchJob?.isActive == true) {
            searchJob?.cancel()
            return
        }

        debounceLoadRepositories(query = value)
    }

    fun loadNextPage() {
        loadPage(state.value.lastPage + 1)
    }

    private fun loadPage(newPage: Int) = viewModelScope.launch {
        fetchRepositories(
            query = state.value.query,
            page = newPage
        ).onStart {
            _state.update { it.copy(loading = LoadingState.Item) }
        }.onEach { response ->
            _state.update {
                val newList = (it.repositories + response).distinctBy { it.id }

                val newStatus = if (newList.size > it.repositories.size) {
                    LastPageStatus.Success
                } else {
                    LastPageStatus.Final
                }

                it.copy(
                    loading = LoadingState.Non,
                    repositories = newList.toPersistentList(),
                    lastPage = newPage,
                    lastPageStatus = newStatus
                )
            }
        }.catch { t ->
            _state.update {
                it.copy(
                    loading = LoadingState.Non,
                    lastPageStatus = LastPageStatus.Broken(t.localizedMessage ?: "-"),
                )
            }
        }.launchIn(this)
    }

    private fun debounceLoadRepositories(query: String) = viewModelScope.launch {
        searchJob?.cancel()
        searchJob = launch {
            delay(DEBOUNCE_TIME_MS)
            fetchRepositories(query = query, page = 1)
                .onStart {
                    _state.update { it.copy(loading = LoadingState.Search) }
                }.onEach { response ->
                    _state.update {
                        it.copy(
                            loading = LoadingState.Non,
                            repositories = response.toPersistentList(),
                            lastPageStatus = LastPageStatus.Success,
                            lastPage = INITIAL_PAGE,
                        )
                    }
                }.catch { t ->
                    _state.update {
                        it.copy(loading = LoadingState.Non, error = t.localizedMessage)
                    }
                }.launchIn(this)
        }
    }

    private fun fetchRepositories(query: String, page: Int) = getRepositoriesUseCase
        .invoke(
            query = query,
            page = page,
            pageSize = PAGE_SIZE
        )

    fun clearError() {
        _state.update { it.copy(error = null) }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
        searchJob = null
    }

    companion object {
        const val LOAD_NEW_PAGE_THRESHOLD = 5L
        private const val DEBOUNCE_TIME_MS = 700L
        private const val QUERY_LENGTH_VALIDATION = 3
        private const val PAGE_SIZE = 20
        private const val INITIAL_PAGE = 1
    }
}