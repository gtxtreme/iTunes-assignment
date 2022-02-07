package com.gtxtreme.template.repo.models

import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.service.content.FavouriteContent
import com.gtxtreme.template.service.content.RemoteContent
import com.gtxtreme.template.service.content.RemoteResult

val testContent = Content(
    artistId = 1,
    thumbnailUrl = "https://google.com",
    artistName = "Arijit Singh",
    "Bekhayali",
    "Random",
    1234
)

val testFavouriteContent = FavouriteContent(artistId = 1, artistName = "Arijit Singh", trackName = "Bekhayali", trackId = 1234)

val testRemoteContent = RemoteContent(artistId = 3)
val testRemoteResult = RemoteResult(resultCount = 4, results = listOf(testRemoteContent))
