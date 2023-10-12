package com.voitenko.testgithubapp.repositorydetails.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzle.pizza.GetRepositoryByIdUseCase
import com.voitenko.testgithubapp.repositorydetails.RepositoryDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RepositoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repositoryByIdUseCase: GetRepositoryByIdUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<RepositoryDetailsState> = MutableStateFlow(
        RepositoryDetailsState(
            repositoryId = savedStateHandle.get<String>(RepositoryDetailsRoute.ARG_REPO_ID) ?: "",
        )
    )
    val state: StateFlow<RepositoryDetailsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repositoryByIdUseCase
                .invoke(state.value.repositoryId)
                .onStart {

                }.onEach {

                }.catch {

                }.launchIn(this)
        }
    }
}