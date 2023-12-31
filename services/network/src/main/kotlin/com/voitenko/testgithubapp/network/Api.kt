package com.voitenko.testgithubapp.network

import com.voitenko.testgithubapp.network.dto.ItemDto
import com.voitenko.testgithubapp.network.dto.RepositoriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("order") order: String = "desc",
        @Query("perPage") perPage: Int = 10,
        @Query("page") page: Int = 1,
    ): RepositoriesDto

    @GET("/repos/{owner}/{name}")
    suspend fun getRepositoryById(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): ItemDto
}
