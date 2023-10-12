package com.puzzle.pizza.repo

import com.puzzle.pizza.models.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepositoriesApi {
    fun getRepositories(query: String): Flow<List<Repository>>
    fun getRepositoryById(name: String, owner: String): Flow<Repository>
}
