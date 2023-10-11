package com.voitenko.testgithubapp.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LicenseDto(
    @SerialName("html_url")
    val htmlUrl: String? = null,
    @SerialName("key")
    val key: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("node_id")
    val nodeId: String? = null,
    @SerialName("spdx_id")
    val spdxId: String? = null,
    @SerialName("url")
    val url: String? = null
)