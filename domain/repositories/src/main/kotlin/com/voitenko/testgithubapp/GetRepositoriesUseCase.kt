package com.voitenko.testgithubapp

import androidx.paging.PagingData
import com.voitenko.testgithubapp.models.Repository
import com.voitenko.testgithubapp.repo.GithubRepositoriesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repo: GithubRepositoriesApi) {

    fun invoke(query: String): Flow<PagingData<Repository>> {
        return repo.getRepositories(query)
    }
}