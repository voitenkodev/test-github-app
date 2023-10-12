package com.puzzle.pizza.repo

import com.puzzle.pizza.models.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepositoriesApi {
    fun getRepositoryById(id: String): Flow<Repository>
    fun getRepositories(query: String): Flow<List<Repository>>
}