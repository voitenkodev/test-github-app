package com.puzzle.pizza

import com.puzzle.pizza.models.Repository
import com.puzzle.pizza.repo.GithubRepositoriesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repo: GithubRepositoriesApi) {

    fun invoke(query: String): Flow<List<Repository>> {
        return repo
            .getRepositories(query)
    }
}