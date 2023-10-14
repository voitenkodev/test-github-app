package com.voitenko.testgithubapp.githubrepositories.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.voitenko.testgithubapp.githubrepositories.components.RepositoryErrorItemContent
import com.voitenko.testgithubapp.githubrepositories.components.RepositoryItemContent
import com.voitenko.testgithubapp.githubrepositories.list.GithubRepositoriesViewModel.Companion.LOAD_NEW_PAGE_THRESHOLD
import components.inputs.InputSearch
import controls.Toast

@Composable
internal fun GithubRepositoriesScreen(
    state: GithubRepositoriesState,
    viewModel: GithubRepositoriesViewModel,
    onDetailsClick: (name: String, owner: String) -> Unit
) {

    val lazyColumnListState = rememberLazyListState()

    val shouldStartPaginate = remember(state.lastPageStatus) {
        derivedStateOf {
            val canPaginate = state.lastPageStatus == LastPageStatus.Success
            val lastVisibleItem = lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            val total = lazyColumnListState.layoutInfo.totalItemsCount
            canPaginate && (lastVisibleItem >= total - LOAD_NEW_PAGE_THRESHOLD)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value) {
            viewModel.loadNextPage()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        InputSearch(
            modifier = Modifier.fillMaxWidth(),
            value = state.query,
            onValueChange = viewModel::setQuery,
            loading = state.loading == LoadingState.Search
        )

        LazyColumn(
            state = lazyColumnListState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(items = state.repositories, key = { it.id }) {
                RepositoryItemContent(
                    modifier = Modifier.fillMaxWidth(),
                    repo = it,
                    onClick = onDetailsClick
                )
            }

            if (state.loading == LoadingState.Item) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(56.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            (state.lastPageStatus as? LastPageStatus.Broken)?.error
                .takeIf { state.loading == LoadingState.Non }
                ?.let { error ->
                    item {
                        RepositoryErrorItemContent(
                            msg = error,
                            retry = viewModel::loadNextPage
                        )
                    }
                }
        }
    }

    if (state.error != null) {
        Toast(message = state.error, clear = viewModel::clearError)
    }
}