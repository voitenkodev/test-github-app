package com.voitenko.testgithubapp.remote

import javax.inject.Inject

class TokenStore @Inject constructor() {

    suspend fun getAuthToken() = "TODO (GET ACCESS TOKEN FROM SOME STORAGE)"
    suspend fun getRefreshToken() = "TODO (GET REFRESH TOKEN FROM SOME STORAGE)"

    suspend fun setAuthToken(value: String?) {
        // TODO (SAVE TOKEN)
    }

    suspend fun setRefreshToken(value: String?) {
        // TODO (SAVE REFRESH TOKEN)
    }
}