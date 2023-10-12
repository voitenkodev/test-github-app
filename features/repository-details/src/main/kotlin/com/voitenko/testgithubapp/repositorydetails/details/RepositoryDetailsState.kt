package com.voitenko.testgithubapp.repositorydetails.details

import com.puzzle.pizza.models.Repository

internal data class RepositoryDetailsState(
    val repository: Repository? = null,

    // Scaffold values
    val loading: Boolean = false,
    val error: String? = null
)