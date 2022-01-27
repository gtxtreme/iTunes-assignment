package com.gtxtreme.template.repo

import com.gtxtreme.template.content.DomainContentMapper
import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.service.itunecontent.MediaContentRemoteService

class ContentRepoImpl(
    private val contentRemoteService: MediaContentRemoteService,
    private val domainContentMapper: DomainContentMapper
) :
    ContentRepository {
    override suspend fun getAllContentByArtistName(artistName: String): List<Content> =
        if (contentRemoteService.getMediaDetailByAuthor(artistName).resultCount == 0) {
            emptyList()
        } else {
            domainContentMapper.map(contentRemoteService.getMediaDetailByAuthor(artistName).results)
        }
}
