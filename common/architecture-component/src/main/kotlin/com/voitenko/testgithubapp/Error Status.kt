package com.voitenko.testgithubapp

sealed class ErrorStatus(override val message: String?) : Throwable(message = message) {
    data object LimitedRequests : ErrorStatus("Limit of requests")
    data object NoInternetConnection : ErrorStatus("No Internet Connection")
    data class ExpectedServerError(override val message: String?) : ErrorStatus(message)
    data class UnExpectedServerError(override val message: String?) : ErrorStatus(message)
    data class UnknownError(override val message: String?) : ErrorStatus(message)
}