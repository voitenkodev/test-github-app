package com.voitenko.testgithubapp.repo

import androidx.paging.PagingData
import com.voitenko.testgithubapp.models.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepositoriesApi {
    fun getRepositories(query: String): Flow<PagingData<Repository>>
    fun getRepositoryById(name: String, owner: String): Flow<Repository>
}
