package com.voitenko.testgithubapp.githubrepositories.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.inputs.InputSearch

@Composable
internal fun GithubRepositoriesScreen(
    state: GithubRepositoriesState,
    viewModel: GithubRepositoriesViewModel
) {
    Column {
        InputSearch(
            modifier = Modifier.fillMaxWidth(),
            value = state.query,
            onValueChange = viewModel::setQuery
        )
    }
}