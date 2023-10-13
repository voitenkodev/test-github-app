package com.puzzle.pizza

import androidx.paging.PagingData
import com.puzzle.pizza.models.Repository
import com.puzzle.pizza.repo.GithubRepositoriesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repo: GithubRepositoriesApi) {

    fun invoke(query: String): Flow<PagingData<Repository>> {
        return repo.getRepositories(query)
    }
}