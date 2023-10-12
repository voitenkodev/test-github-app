package com.puzzle.pizza.repo

import com.puzzle.pizza.models.Repository
import com.puzzle.pizza.models.toDomain
import com.voitenko.testgithubapp.remote.RemoteDataSource
import com.voitenko.testgithubapp.remote.di.IoDispatcher
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import javax.inject.Singleton

@BoundTo(supertype = GithubRepositoriesApi::class)
@Singleton
private class GithubRepositoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : GithubRepositoriesApi {

    override fun getRepositories(query: String): Flow<List<Repository>> {
        return remoteDataSource
            .getRepositories(query)
            .mapNotNull { it.items?.toDomain() }
            .flowOn(dispatcher)
    }

    override fun getRepositoryById(id: String): Flow<Repository> {
        return remoteDataSource
            .getRepositoryById(id)
            .map { it.toDomain() }
            .flowOn(dispatcher)
    }
}