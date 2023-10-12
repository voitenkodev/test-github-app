package com.voitenko.testgithubapp.repositorydetails

import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.voitenko.testgithubapp.NavParams
import com.voitenko.testgithubapp.NavRoute
import com.voitenko.testgithubapp.repositorydetails.details.RepositoryDetailsScreen
import com.voitenko.testgithubapp.repositorydetails.details.RepositoryDetailsViewModel

object RepositoryDetailsRoute : NavRoute<RepositoryDetailsRoute.Params>() {

    private const val PATH = "repository/details"
    internal const val NAME = "name"
    internal const val OWNER = "owner"

    override val route: String = "$PATH?$NAME={$NAME}&$OWNER={$OWNER}"

    override fun build(params: Params): String {
        return Uri.Builder()
            .path(PATH)
            .appendQueryParameter(NAME, params.name)
            .appendQueryParameter(OWNER, params.owner)
            .build()
            .toString()
    }

    class Params(
        val name: String,
        val owner: String,
    ) : NavParams
}

fun NavGraphBuilder.repositoryDetailsScreen() {
    composable(
        RepositoryDetailsRoute.route,
        arguments = listOf(
            navArgument(RepositoryDetailsRoute.NAME) { },
            navArgument(RepositoryDetailsRoute.OWNER) { }
        ),
    ) {

        val viewModel = hiltViewModel<RepositoryDetailsViewModel>()
        val state by viewModel.state.collectAsState()

        RepositoryDetailsScreen(
            viewModel = viewModel,
            state = state
        )
    }
}
