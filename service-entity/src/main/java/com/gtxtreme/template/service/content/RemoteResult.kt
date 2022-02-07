package com.gtxtreme.template.service.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    @SerialName("resultCount")
    var resultCount: Int? = null,
    @SerialName("results")
    var results: List<RemoteContent> = arrayListOf()

)
