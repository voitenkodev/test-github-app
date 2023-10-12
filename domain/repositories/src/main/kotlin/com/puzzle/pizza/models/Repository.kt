package com.puzzle.pizza.models

import com.voitenko.testgithubapp.remote.dto.ItemDto

data class Repository(
    val id: Int,
    val owner: String,
    val name: String,
    val language: String? = null,
    val description: String? = null,
    val fullName: String? = null,
    val stargazersCount: Int? = null,
    val createdAt: String? = null,
    val openIssues: Int? = null,
)

fun List<ItemDto>.toDomain() = mapNotNull { it.toDomain() }

fun ItemDto.toDomain(): Repository? {
    return Repository(
        id = id ?: return null,
        owner = owner?.login ?: return null,
        name = name ?: return null,
        language = language,
        description = description,
        fullName = fullName,
        stargazersCount = stargazersCount,
        createdAt = createdAt,
        openIssues = openIssues,
    )
}