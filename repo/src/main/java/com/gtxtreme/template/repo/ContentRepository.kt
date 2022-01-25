package com.gtxtreme.template.repo

import com.gtxtreme.template.domain.content.Content

interface ContentRepository {

    suspend fun getAllContentByArtistName(artistName: String): List<Content>
}
