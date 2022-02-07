package com.gtxtreme.template.content

import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.service.content.FavouriteContent
import com.gtxtreme.template.service.content.RemoteContent
import com.gtxtreme.template.util.Mapper

interface DomainContentMapper : Mapper<RemoteContent, Content> {

    fun mapContent(from: Content): FavouriteContent
    fun mapFavouriteContent(from: FavouriteContent): Content
    fun mapFavouriteContentList(from: List<FavouriteContent>): List<Content> = from.map(::mapFavouriteContent)
}

class DomainContentMapperImpl : DomainContentMapper {
    override fun mapContent(from: Content): FavouriteContent {
        return FavouriteContent(artistId = from.artistId, artistName = from.artistName, trackName = from.trackName, trackId = from.trackId)
    }

    override fun mapFavouriteContent(from: FavouriteContent): Content {
        return Content(from.artistId, "", from.artistName, from.trackName, "", from.trackId)
    }

    override fun map(from: RemoteContent): Content = Content(
        from.artistId ?: 0,
        from.artworkUrl100 ?: "",
        from.artistName ?: "",
        from.trackName ?: "",
        from.artistName ?: "",
        from.trackId ?: 0,
    )
}
