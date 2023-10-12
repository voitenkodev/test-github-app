package com.voitenko.testgithubapp.repositorydetails.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.voitenko.testgithubapp.repositorydetails.RepositoryDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class RepositoryDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _state: MutableStateFlow<RepositoryDetailsState> = MutableStateFlow(
        RepositoryDetailsState(
            repositoryId = savedStateHandle.get<String>(RepositoryDetailsRoute.ARG_REPO_ID) ?: "",
        )
    )
    val state: StateFlow<RepositoryDetailsState> = _state.asStateFlow()
}