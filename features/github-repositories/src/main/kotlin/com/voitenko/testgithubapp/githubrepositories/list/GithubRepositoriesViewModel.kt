package com.voitenko.testgithubapp.githubrepositories.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.puzzle.pizza.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    fun setQuery(value: String) {
        _state.update { it.copy(query = value) }
    }

    fun fetchList() {

    }
}