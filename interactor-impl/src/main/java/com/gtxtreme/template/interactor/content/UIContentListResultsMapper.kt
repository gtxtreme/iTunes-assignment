package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.interactor.base.Mapper
import com.gtxtreme.template.presentation.base.UIList
import com.gtxtreme.template.presentation.content.UIContent

class UIContentListResultsMapper : Mapper<List<Content>, UIList> {
    override fun map(from: List<Content>): UIList =
        UIList(
            from.map { domainContent ->
                UIContent(
                    "UIContent ${domainContent.artistId}",
                    domainContent.artistName,
                    domainContent.trackName
                )
            }.toList()
        )
}
