package com.gtxtreme.template.repo

import com.gtxtreme.template.content.DomainContentMapper
import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.service.content.MediaContentLocalService
import com.gtxtreme.template.service.itunecontent.MediaContentRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class ContentRepoImpl(
    private val contentRemoteService: MediaContentRemoteService,
    private val contentLocalService: MediaContentLocalService,
    private val domainContentMapper: DomainContentMapper,
) :
    ContentRepository {

    override suspend fun getAllContentByArtistName(artistName: String): List<Content> {
        val response = contentRemoteService.getMediaDetailByAuthor(artistName)
        return if (response.resultCount == 0) {
            emptyList()
        } else {
            domainContentMapper.map(response.results)
        }
    }

    override fun getFavoriteContentFlow(): Flow<List<Content>> =
        contentLocalService.getAllFavouriteContentFlow()
            .map(domainContentMapper::mapFavouriteContentList)

    override suspend fun markContentAsFavourite(content: Content) {
        Timber.d("Marking Content as Favourite: ${content.trackName}")
        contentLocalService.markContentAsFavourite(domainContentMapper.mapContent(content))
    }

    override suspend fun unmarkContentAsFavourite(content: Content) {
        Timber.d("Removing Content As Favourite: ${content.trackName}")
        contentLocalService.removeContentAsFavourite(domainContentMapper.mapContent(content))
    }
}
