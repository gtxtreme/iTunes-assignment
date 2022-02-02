package com.gtxtreme.template.repo

import com.gtxtreme.template.domain.content.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {

    suspend fun getAllContentByArtistName(artistName: String): List<Content>

    fun getFavoriteContentFlow(): Flow<List<Content>>

    suspend fun markContentAsFavourite(content: Content)

    suspend fun unmarkContentAsFavourite(content: Content)
}
