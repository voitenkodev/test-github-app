package com.voitenko.testgithubapp.githubrepository.models

data class Item(
    val id: Int? = null,
    val name: String? = null,
    val owner: Owner? = null,
    val createdAt: String? = null,
    val description: String? = null,
    val fullName: String? = null,
    val language: String? = null,
    val openIssues: Int? = null,
    val stargazersCount: Int? = null,
)