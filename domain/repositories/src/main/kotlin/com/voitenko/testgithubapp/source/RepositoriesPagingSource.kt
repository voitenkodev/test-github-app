package com.voitenko.testgithubapp.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.voitenko.testgithubapp.remote.RemoteDataSource
import com.voitenko.testgithubapp.remote.dto.ItemDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class RepositoriesPagingSource @AssistedInject constructor(
    private val remote: RemoteDataSource,
    @Assisted private val query: String = ""
) : PagingSource<Int, ItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemDto> {

        if (query.length < MINIMAL_QUERY_LENGTH) {
            return LoadResult.Page(emptyList(), null, null)
        }

        val page = params.key ?: STARTING_PAGE_INDEX
        val pageSize = params.loadSize

        try {

            val response = remote.getRepositories(
                query = query,
                pageSize = NETWORK_PAGE_SIZE,
                page = page
            )

            val result = response.items
                ?: return LoadResult.Page(emptyList(), null, null)

            val nextKey = if ((response.items?.size ?: 0) < pageSize) null else page + 1

            val prevKey = if (page == 1) null else page - 1

            return LoadResult.Page(
                data = result,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (t: Throwable) {
            return LoadResult.Error(t)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(query: String): RepositoriesPagingSource
    }
}