package com.voitenko.testgithubapp.githubrepositories.list

import com.puzzle.pizza.models.Repository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class GithubRepositoriesState(
    val query: String = "kotlin",
    val repositories: ImmutableList<Repository> = persistentListOf(),

    // Scaffold values
    val loading: Boolean = false,
    val error: String? = null
)