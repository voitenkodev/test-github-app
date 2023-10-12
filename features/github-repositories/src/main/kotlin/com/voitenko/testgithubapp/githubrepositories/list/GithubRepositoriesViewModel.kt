package com.voitenko.testgithubapp.githubrepositories.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzle.pizza.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    savedStateHandle: SavedStateHandle,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<GithubRepositoriesState> = MutableStateFlow(
        GithubRepositoriesState()
    )
    val state: StateFlow<GithubRepositoriesState> = _state.asStateFlow()

    init {
        fetchList()
    }

    fun setQuery(value: String) {
        _state.update { it.copy(query = value) }
    }

    private fun fetchList() {
        viewModelScope.launch {
            getRepositoriesUseCase
                .invoke(query = state.value.query)
                .onStart {


                }.onEach {

                }.catch {

                }.launchIn(this)
        }
    }
}