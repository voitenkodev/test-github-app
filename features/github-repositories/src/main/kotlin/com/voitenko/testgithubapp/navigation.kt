package com.voitenko.testgithubapp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.voitenko.testgithubapp.details.GithubListContent

sealed class GithubRepositoriesDirections(var route: String) {
    data object MainList : GithubRepositoriesDirections("/githubrepositories")
}

fun NavGraphBuilder.githubListGraph(
    navController: NavController
) {
    composable(GithubRepositoriesDirections.MainList.route) {
        GithubListContent()
    }
}