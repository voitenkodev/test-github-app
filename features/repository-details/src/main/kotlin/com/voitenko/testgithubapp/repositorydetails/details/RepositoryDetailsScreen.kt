package com.voitenko.testgithubapp.repositorydetails.details

import Design
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.Error
import components.Loading
import components.texts.TextBody1
import components.texts.TextBody2
import components.texts.TextHead2

@Composable
internal fun RepositoryDetailsScreen(
    state: RepositoryDetailsState,
    viewModel: RepositoryDetailsViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TextHead2(text = state.repository?.fullName ?: "-")

        TextBody1(text = state.repository?.description ?: "-")

        TextBody2(
            text = "Stars - ${state.repository?.stargazersCount}",
            color = Design.colors.caption
        )

        TextBody2(
            text = "Language - ${state.repository?.language}",
            color = Design.colors.caption
        )

        TextBody2(
            text = "Opened Issues - ${state.repository?.openIssues}",
            color = Design.colors.caption
        )

        TextBody2(
            text = "Created Ap - ${state.repository?.createdAt}",
            color = Design.colors.caption
        )
    }

    Error(message = state.error, close = viewModel::clearErrors)

    Loading(visible = state.loading)
}