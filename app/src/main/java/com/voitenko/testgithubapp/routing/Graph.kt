package com.voitenko.testgithubapp.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.voitenko.testgithubapp.githubrepositories.GithubRepositoriesRoute
import com.voitenko.testgithubapp.githubrepositories.githubRepositoriesScreen
import com.voitenko.testgithubapp.repositorydetails.repositoryDetailsScreen

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = GithubRepositoriesRoute.route,
        builder = {

            githubRepositoriesScreen()

            repositoryDetailsScreen()

        }
    )
}