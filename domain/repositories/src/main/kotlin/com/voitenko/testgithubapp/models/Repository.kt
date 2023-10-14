package com.voitenko.testgithubapp.models

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