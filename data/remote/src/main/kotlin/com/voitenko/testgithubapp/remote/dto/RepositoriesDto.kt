package com.voitenko.testgithubapp.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoriesDto(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean? = null,
    @SerialName("items")
    val items: List<ItemDto?>? = null,
    @SerialName("total_count")
    val totalCount: Int? = null
)



