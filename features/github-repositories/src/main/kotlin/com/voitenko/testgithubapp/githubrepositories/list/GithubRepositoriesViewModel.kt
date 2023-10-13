package com.voitenko.testgithubapp.githubrepositories.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.voitenko.testgithubapp.GetRepositoriesUseCase
import com.voitenko.testgithubapp.models.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class GithubRepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<GithubRepositoriesState> = MutableStateFlow(GithubRepositoriesState())
    val state: StateFlow<GithubRepositoriesState> = _state.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val repositories: Flow<PagingData<Repository>> = state
        .map { it.query }
        .debounce(DEBOUNCE_TIME_MS)
        .distinctUntilChanged()
        .flatMapLatest { query -> getRepositoriesUseCase.invoke(query) }
        .cachedIn(viewModelScope)

    fun setQuery(value: String) {
        _state.update { it.copy(query = value) }
    }

    companion object {
        private const val DEBOUNCE_TIME_MS = 700L
    }
}