package com.puzzle.pizza.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.puzzle.pizza.models.Repository
import com.puzzle.pizza.models.toDomain
import com.puzzle.pizza.source.INITIAL_LOAD_SIZE
import com.puzzle.pizza.source.NETWORK_PAGE_SIZE
import com.puzzle.pizza.source.PREFETCH_DISTANCE
import com.puzzle.pizza.source.RepositoriesPagingSource
import com.voitenko.testgithubapp.remote.RemoteDataSource
import com.voitenko.testgithubapp.remote.di.IoDispatcher
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
    private val repositoriesPagingSource: RepositoriesPagingSource.Factory
) : GithubRepositoriesApi {

    override fun getRepositories(query: String): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE,
            ),
            pagingSourceFactory = { repositoriesPagingSource.create(query) }
        ).flow
            .mapNotNull { it.map { it.toDomain() } }
            .flowOn(dispatcher)
    }

    override fun getRepositoryById(name: String, owner: String): Flow<Repository> {
        return remoteDataSource
            .getRepositoryById(name, owner)
            .mapNotNull { it.toDomain() }
            .flowOn(dispatcher)
    }
}