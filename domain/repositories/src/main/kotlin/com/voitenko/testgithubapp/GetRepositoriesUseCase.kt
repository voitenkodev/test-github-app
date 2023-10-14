package com.voitenko.testgithubapp

import com.voitenko.testgithubapp.models.Repository
import com.voitenko.testgithubapp.repo.GithubRepositoriesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repo: GithubRepositoriesApi) {

    fun invoke(
        query: String,
        page: Int,
        pageSize: Int
    ): Flow<List<Repository>> {
        return repo.getRepositories(query, page, pageSize)
    }
}