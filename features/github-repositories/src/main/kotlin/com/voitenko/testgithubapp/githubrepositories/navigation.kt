package com.voitenko.testgithubapp.githubrepositories

import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.voitenko.testgithubapp.EmptyNavParams
import com.voitenko.testgithubapp.NavRoute
import com.voitenko.testgithubapp.githubrepositories.list.GithubRepositoriesScreen
import com.voitenko.testgithubapp.githubrepositories.list.GithubRepositoriesViewModel

object GithubRepositoriesRoute : NavRoute<EmptyNavParams>() {

    private const val PATH = "repository/list"
    override val route: String = PATH
    override fun build(params: EmptyNavParams): String {
        return Uri.Builder()
            .path(PATH)
            .build()
            .toString()
    }
}

fun NavGraphBuilder.githubRepositoriesScreen() {
    composable(GithubRepositoriesRoute.route) {
        val viewModel = hiltViewModel<GithubRepositoriesViewModel>()
        val state by viewModel.state.collectAsState()

        GithubRepositoriesScreen(
            viewModel = viewModel,
            state = state
        )
    }
}
