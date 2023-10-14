package com.voitenko.testgithubapp.githubrepository

import com.voitenko.testgithubapp.githubrepository.mapping.toDomain
import com.voitenko.testgithubapp.models.Repository
import com.voitenko.testgithubapp.network.RemoteDataSource
import com.voitenko.testgithubapp.network.di.IoDispatcher
import com.voitenko.testgithubapp.repo.GithubRepositoriesApi
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject
import javax.inject.Singleton

@BoundTo(supertype = GithubRepositoriesApi::class)
@Singleton
private class GithubRepositoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : GithubRepositoriesApi {

    override fun getRepositories(query: String, page: Int, pageSize: Int): Flow<List<Repository>> {
        return remoteDataSource.getRepositories(
            query = query,
            pageSize = pageSize,
            page = page
        ).mapNotNull { it.items?.map { it.toDomain() } }
            .flowOn(dispatcher)
    }

    override fun getRepositoryById(name: String, owner: String): Flow<Repository> {
        return remoteDataSource
            .getRepositoryById(name, owner)
            .mapNotNull { it.toDomain() }
            .flowOn(dispatcher)
    }
}