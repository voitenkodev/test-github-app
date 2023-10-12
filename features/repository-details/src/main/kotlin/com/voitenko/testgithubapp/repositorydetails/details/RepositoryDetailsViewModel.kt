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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RepositoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repositoryByIdUseCase: GetRepositoryByIdUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<RepositoryDetailsState> = MutableStateFlow(RepositoryDetailsState())
    val state: StateFlow<RepositoryDetailsState> = _state.asStateFlow()

    init {
        val id = savedStateHandle.get<String>(RepositoryDetailsRoute.NAME) ?: ""
        val owner = savedStateHandle.get<String>(RepositoryDetailsRoute.OWNER) ?: ""
        viewModelScope.launch {
            repositoryByIdUseCase
                .invoke(id, owner)
                .onStart {
                    _state.update { it.copy(loading = true) }
                }.onEach { response ->
                    _state.update {
                        it.copy(
                            repository = response,
                            loading = false
                        )
                    }
                }.catch { t ->
                    _state.update { it.copy(loading = false, error = t.localizedMessage) }
                }.launchIn(this)
        }
    }

    fun clearErrors() {
        _state.update { it.copy(error = null) }
    }
}