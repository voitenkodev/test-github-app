package com.voitenko.testgithubapp

import com.voitenko.testgithubapp.models.Repository
import com.voitenko.testgithubapp.repo.GithubRepositoriesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryByIdUseCase @Inject constructor(private val repo: GithubRepositoriesApi) {

    fun invoke(
        name: String,
        owner: String,
        page: Int = 0,
        pageSize: Int = 20
    ): Flow<Repository> {
        return repo.getRepositoryById(name, owner)
    }
}