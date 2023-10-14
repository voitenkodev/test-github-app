package com.voitenko.testgithubapp.githubrepositories.list

import com.voitenko.testgithubapp.models.Repository
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

internal data class GithubRepositoriesState(
    val query: String = "",
    val repositories: PersistentList<Repository> = persistentListOf(),
    val lastPageStatus: LastPageStatus = LastPageStatus.Initial,
    val lastPage: Int = 1,

    val loading: LoadingState = LoadingState.Non,
    val error: String? = null,
)

enum class LoadingState { Non, Search, Item }
sealed class LastPageStatus {
    data object Initial : LastPageStatus()
    data object Success : LastPageStatus()
    data object Final : LastPageStatus()
    data class Broken(val error: String) : LastPageStatus()
}
