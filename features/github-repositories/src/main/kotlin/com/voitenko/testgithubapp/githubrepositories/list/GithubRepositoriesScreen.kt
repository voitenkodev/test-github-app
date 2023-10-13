package com.voitenko.testgithubapp.githubrepositories.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.puzzle.pizza.models.Repository
import com.voitenko.testgithubapp.githubrepositories.components.RepositoryErrorItemContent
import com.voitenko.testgithubapp.githubrepositories.components.RepositoryItemContent
import components.inputs.InputSearch
import controls.Toast

@Composable
internal fun GithubRepositoriesScreen(
    state: GithubRepositoriesState,
    viewModel: GithubRepositoriesViewModel,
    onDetailsClick: (name: String, owner: String) -> Unit
) {

    val repositories: LazyPagingItems<Repository> = viewModel.repositories.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {

        InputSearch(
            modifier = Modifier.fillMaxWidth(),
            value = state.query,
            onValueChange = viewModel::setQuery,
            loading = repositories.loadState.refresh is LoadState.Loading
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                count = repositories.itemCount,
                contentType = repositories.itemContentType { "Repository" }
            ) { index ->

                val repository = repositories[index]

                if (repository != null) {
                    RepositoryItemContent(
                        modifier = Modifier.fillMaxWidth(),
                        repo = repository,
                        onClick = onDetailsClick
                    )
                }
            }

            if (repositories.loadState.append is LoadState.Loading) {
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

            (repositories.loadState.append as? LoadState.Error)?.error?.localizedMessage?.let { error ->
                item {
                    RepositoryErrorItemContent(
                        msg = error,
                        retry = repositories::retry
                    )
                }
            }
        }
    }

    (repositories.loadState.refresh as? LoadState.Error)?.error?.localizedMessage?.let { error ->
        Toast(message = error)
    }
}