package com.voitenko.testgithubapp.network

import com.voitenko.testgithubapp.ErrorStatus
import com.voitenko.testgithubapp.network.dto.ItemDto
import com.voitenko.testgithubapp.network.dto.RepositoriesDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class RemoteDataSource(private val api: Api) {

    fun getRepositories(query: String, page: Int, pageSize: Int): Flow<RepositoriesDto> {
        return flow {
            emit(api.getRepositories(query = query, perPage = pageSize, page = page))
        }.parseErrorMessage()
    }

    fun getRepositoryById(name: String, owner: String): Flow<ItemDto> {
        return flow {
            emit(api.getRepositoryById(name = name, owner = owner))
        }.parseErrorMessage()
    }

    private inline fun <reified T> Flow<T>.parseErrorMessage() = this.catch {
        it.parseErrorMessage()
    }

    private fun Throwable.parseErrorMessage() {
        val isLimitRequests = (this as? HttpException)?.response()?.code() == 403

        val isUnExpectedServerError = (this as? HttpException)?.response()?.code() in 500..599

        if (isLimitRequests) {
            throw ErrorStatus.LimitedRequests
        } else if (this is UnknownHostException || this is IOException)
            throw ErrorStatus.NoInternetConnection
        else if (isUnExpectedServerError) {
            throw ErrorStatus.UnExpectedServerError("Server is unavailable")
        } else if (this is HttpException) {
            throw ErrorStatus.ExpectedServerError(
                (this as? HttpException)?.response()?.errorBody()?.string() ?: "Unknown server error (HttpException)"
            )
        } else {
            throw ErrorStatus.UnknownError(this.localizedMessage ?: "Unknown error")
        }
    }
}