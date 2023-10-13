package com.puzzle.pizza.repo

import androidx.paging.PagingData
import com.puzzle.pizza.models.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepositoriesApi {
    fun getRepositories(query: String): Flow<PagingData<Repository>>
    fun getRepositoryById(name: String, owner: String): Flow<Repository>
}
