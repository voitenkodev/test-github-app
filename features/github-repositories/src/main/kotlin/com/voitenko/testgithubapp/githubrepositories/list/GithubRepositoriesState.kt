package com.voitenko.testgithubapp.githubrepositories.list

internal data class GithubRepositoriesState(
    val query: String = "",

    val loading: Boolean = false
)