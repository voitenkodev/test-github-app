package com.voitenko.testgithubapp.repositorydetails.details

import com.voitenko.testgithubapp.models.Repository

internal data class RepositoryDetailsState(
    val repository: Repository? = null,

    // Scaffold values
    val loading: Boolean = false,
    val error: String? = null
)