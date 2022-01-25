package com.gtxtreme.template.service.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteContent (

    @SerialName("wrapperType")
    var wrapperType: String? = null,
    @SerialName("kind")
    var kind: String? = null,
    @SerialName("artistId")
    var artistId: Int? = null,
    @SerialName("collectionId")
    var collectionId: Int? = null,
    @SerialName("trackId")
    var trackId: Int? = null,
    @SerialName("artistName")
    var artistName: String? = null,
    @SerialName("collectionName")
    var collectionName: String? = null,
    @SerialName("trackName")
    var trackName: String? = null,
    @SerialName("collectionCensoredName")
    var collectionCensoredName: String? = null,
    @SerialName("trackCensoredName")
    var trackCensoredName: String? = null,
    @SerialName("artistViewUrl")
    var artistViewUrl: String? = null,
    @SerialName("collectionViewUrl")
    var collectionViewUrl: String? = null,
    @SerialName("trackViewUrl")
    var trackViewUrl: String? = null,
    @SerialName("previewUrl")
    var previewUrl: String? = null,
    @SerialName("artworkUrl30")
    var artworkUrl30: String? = null,
    @SerialName("artworkUrl60")
    var artworkUrl60: String? = null,
    @SerialName("artworkUrl100")
    var artworkUrl100: String? = null,
    @SerialName("collectionPrice")
    var collectionPrice: Double? = null,
    @SerialName("trackPrice")
    var trackPrice: Double? = null,
    @SerialName("releaseDate")
    var releaseDate: String? = null,
    @SerialName("collectionExplicitness")
    var collectionExplicitness: String? = null,
    @SerialName("trackExplicitness")
    var trackExplicitness: String? = null,
    @SerialName("discCount")
    var discCount: Int? = null,
    @SerialName("discNumber")
    var discNumber: Int? = null,
    @SerialName("trackCount")
    var trackCount: Int? = null,
    @SerialName("trackNumber")
    var trackNumber: Int? = null,
    @SerialName("trackTimeMillis")
    var trackTimeMillis: Int? = null,
    @SerialName("country")
    var country: String? = null,
    @SerialName("currency")
    var currency: String? = null,
    @SerialName("primaryGenreName")
    var primaryGenreName: String? = null,
    @SerialName("isStreamable")
    var isStreamable: Boolean? = null
)