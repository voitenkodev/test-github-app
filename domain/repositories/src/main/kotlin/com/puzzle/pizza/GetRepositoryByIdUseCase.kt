package com.puzzle.pizza

import com.puzzle.pizza.models.Repository
import com.puzzle.pizza.repo.GithubRepositoriesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryByIdUseCase @Inject constructor(private val repo: GithubRepositoriesApi) {

    fun invoke(name: String, owner: String): Flow<Repository> {
        return repo
            .getRepositoryById(name, owner)
    }
}