package com.voitenko.testgithubapp.githubrepository.mapping

import com.voitenko.testgithubapp.models.Repository
import com.voitenko.testgithubapp.network.dto.ItemDto

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