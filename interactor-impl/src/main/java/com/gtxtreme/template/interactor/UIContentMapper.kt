package com.gtxtreme.template.interactor

import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.interactor.base.Mapper2
import com.gtxtreme.template.presentation.content.UIContent

interface UIContentMapper : Mapper2<Content, Boolean, UIContent> {
    fun mapContent(from: UIContent): Content
    fun mapFavouriteContent(from: Content): UIContent
    fun mapFavouriteContent(from: List<Content>): List<UIContent> = from.map(::mapFavouriteContent)
}

class UIContentMapperImpl : UIContentMapper {

    override fun mapContent(from: UIContent): Content {
        return Content(from.artistId, "", from.artistName, from.trackName, "", from.trackId)
    }

    override fun mapFavouriteContent(from: Content): UIContent {
        return UIContent(from.artistId, from.artistName, from.trackName, from.trackId, true)
    }

    override fun map(from1: Content, from2: Boolean): UIContent {
        return UIContent(from1.artistId, from1.artistName, from1.trackName, from1.trackId, from2)
    }
}
