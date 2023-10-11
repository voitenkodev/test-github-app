package com.voitenko.testgithubapp.remote

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(private val tokenStore: TokenStore) : Authenticator {

    // TODO USING REFRESH TOKEN UPDATE ACCESS TOKEN OR CLEAR TOKENS IN STORAGE
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {

            // hardcode new token
            val newTokens = retrofit2.Response.success(
                "newHardcodedAccessToken" to "newHardcodedRefreshToken"
            )

            if (!newTokens.isSuccessful || newTokens.body() == null) {
                tokenStore.setAuthToken(null)
                tokenStore.setRefreshToken(null)
            }

            newTokens.body()?.let {
                tokenStore.setAuthToken(it.first)
                tokenStore.setRefreshToken(it.second)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.first}")
                    .build()
            }
        }
    }
}