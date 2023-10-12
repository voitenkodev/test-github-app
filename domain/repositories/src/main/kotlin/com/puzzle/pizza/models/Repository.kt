package com.puzzle.pizza.models

import com.voitenko.testgithubapp.remote.dto.ItemDto

data class Repository(
    val id: Int? = null,
    val language: String? = null,
    val description: String? = null,
    val fullName: String? = null,
    val stargazersCount: Int? = null,
    val createdAt: String? = null,
    val openIssues: Int? = null,
)

fun List<ItemDto>.toDomain() = map { it.toDomain() }

fun ItemDto.toDomain() = Repository(
    id = id,
    language = language,
    description = description,
    fullName = fullName,
    stargazersCount = stargazersCount,
    createdAt = createdAt,
    openIssues = openIssues
)