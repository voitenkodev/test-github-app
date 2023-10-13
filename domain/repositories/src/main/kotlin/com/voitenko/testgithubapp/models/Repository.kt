package com.voitenko.testgithubapp.models

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
) {
    companion object {
        val EMPTY = Repository(
            id = -1,
            owner = "",
            name = ""
        )
    }
}

fun ItemDto.toDomain(): Repository {
    return Repository(
        id = id ?: return Repository.EMPTY,
        owner = owner?.login ?: return Repository.EMPTY,
        name = name ?: return Repository.EMPTY,
        language = language,
        description = description,
        fullName = fullName,
        stargazersCount = stargazersCount,
        createdAt = createdAt,
        openIssues = openIssues,
    )
}