package com.voitenko.testgithubapp.githubrepositories.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.voitenko.testgithubapp.githubrepositories.components.RepositoryItemContent
import components.Error
import components.inputs.InputSearch

@Composable
internal fun GithubRepositoriesScreen(
    state: GithubRepositoriesState,
    viewModel: GithubRepositoriesViewModel,
    onDetailsClick: (name: String, owner: String) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {

        InputSearch(
            modifier = Modifier.fillMaxWidth(),
            value = state.query,
            onValueChange = viewModel::setQuery,
            loading = state.loading
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(state.repositories, key = { it.id }) {
                RepositoryItemContent(
                    modifier = Modifier.fillMaxWidth(),
                    repo = it,
                    onClick = onDetailsClick
                )
            }
        }
    }

    Error(message = state.error, close = viewModel::clearErrors)
}