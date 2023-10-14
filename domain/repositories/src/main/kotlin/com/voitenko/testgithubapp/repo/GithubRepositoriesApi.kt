package com.voitenko.testgithubapp.repo

import com.voitenko.testgithubapp.models.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepositoriesApi {
    fun getRepositories(query: String, page: Int, pageSize: Int): Flow<List<Repository>>
    fun getRepositoryById(name: String, owner: String): Flow<Repository>
}
