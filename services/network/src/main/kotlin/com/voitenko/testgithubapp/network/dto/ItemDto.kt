package com.voitenko.testgithubapp.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("owner")
    val owner: OwnerDto? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("full_name")
    val fullName: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("open_issues")
    val openIssues: Int? = null,
    @SerialName("stargazers_count")
    val stargazersCount: Int? = null,
)