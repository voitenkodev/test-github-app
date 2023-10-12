package com.voitenko.testgithubapp.remote

import com.voitenko.testgithubapp.ErrorStatus
import com.voitenko.testgithubapp.remote.dto.ItemDto
import com.voitenko.testgithubapp.remote.dto.RepositoriesDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class RemoteDataSource(private val api: Api) {

    fun getRepositories(query: String): Flow<RepositoriesDto> {
        return flow { emit(api.getRepositories(query = query)) }.parseErrorMessage()
    }

    fun getRepositoryById(name: String, owner: String): Flow<ItemDto> {
        return flow { emit(api.getRepositoryById(name = name, owner = owner)) }.parseErrorMessage()
    }

    private inline fun <reified T> Flow<T>.parseErrorMessage() = this.catch {
        val isLimitRequests = (it as? HttpException)?.response()?.code() == 403

        val isUnExpectedServerError = (it as? HttpException)?.response()?.code() in 500..599

        if (isLimitRequests) {
            throw ErrorStatus.LimitedRequests
        } else if (it is UnknownHostException || it is IOException)
            throw ErrorStatus.NoInternetConnection
        else if (isUnExpectedServerError) {
            throw ErrorStatus.UnExpectedServerError("Server is unavailable")
        } else if (it is HttpException) {
            throw ErrorStatus.ExpectedServerError(
                (it as? HttpException)?.response()?.errorBody()?.string() ?: "Unknown server error (HttpException)"
            )
        } else {
            throw ErrorStatus.UnknownError(it.localizedMessage ?: "Unknown error")
        }
    }
}