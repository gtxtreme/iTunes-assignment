package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.interactor.UIContentMapper
import com.gtxtreme.template.interactor.base.Mapper2
import com.gtxtreme.template.presentation.base.UIList

interface UIContentListResultsMapper : Mapper2<List<Content>, List<Content>, UIList>

class UIContentListResultsMapperImpl(private val uiContentMapper: UIContentMapper) :
    UIContentListResultsMapper {

    override fun map(from1: List<Content>, from2: List<Content>): UIList {
        val contentList = from2.map { favouriteContent ->
            val isFavourite = from1.contains(favouriteContent)
            uiContentMapper.map(favouriteContent, isFavourite)
        }
        return UIList(contentList)
    }
}
