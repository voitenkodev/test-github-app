package com.voitenko.testgithubapp.remote

import com.voitenko.testgithubapp.remote.dto.ItemDto
import com.voitenko.testgithubapp.remote.dto.RepositoriesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {
    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("order") order: String = "desc",
        @Query("perPage") perPage: Int = 30,
    ): RepositoriesDto

    @GET("/search/repositories")
    suspend fun getRepositoryById(
        @Header("X-GitHub-Api-Version") version: String = "2022-11-28",
        @Query("id") id: String
    ): ItemDto
}
