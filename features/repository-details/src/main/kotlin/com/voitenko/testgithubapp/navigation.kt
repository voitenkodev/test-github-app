package com.voitenko.testgithubapp

import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.voitenko.testgithubapp.details.RepositoryDetailsScreen
import com.voitenko.testgithubapp.details.RepositoryDetailsViewModel
import com.voitenko.testgithubapp.remote.NavParams
import com.voitenko.testgithubapp.remote.NavRoute

object RepositoryDetailsRoute : NavRoute<RepositoryDetailsRoute.Params>() {

    private const val PATH = "repository/details"
    internal const val ARG_REPO_ID = "repositoryId"
    override val route: String = "$PATH?$ARG_REPO_ID={$ARG_REPO_ID}"

    override fun build(params: Params): String {
        return Uri.Builder()
            .path(PATH)
            .appendQueryParameter(ARG_REPO_ID, params.repositoryId)
            .build()
            .toString()
    }

    class Params(val repositoryId: String) : NavParams
}

fun NavGraphBuilder.repositoryDetailsScreen(
) {
    composable(
        RepositoryDetailsRoute.route,
        arguments = listOf(navArgument(RepositoryDetailsRoute.ARG_REPO_ID) { }),
    ) {
        val viewModel = hiltViewModel<RepositoryDetailsViewModel>()
        val state by viewModel.state.collectAsState()

        RepositoryDetailsScreen(
            viewModel = viewModel,
            state = state
        )
    }
}
