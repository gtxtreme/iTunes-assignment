package com.gtxtreme.template.service.content


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteContent (
        @SerialName("resultCount")
        var resultCount: Int? = null,
        @SerialName("results")
        var results: List<Content> = arrayListOf()

)