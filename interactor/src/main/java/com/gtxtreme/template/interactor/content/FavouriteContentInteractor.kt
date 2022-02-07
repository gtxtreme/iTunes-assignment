package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.presentation.base.UIList
import com.gtxtreme.template.presentation.content.UIContent
import kotlinx.coroutines.flow.Flow

interface FavouriteContentInteractor {

    suspend fun markContentAsFavourite(content: UIContent)

    suspend fun removeContentAsFavourite(content: UIContent)

    fun getAllFavouriteContent(): Flow<List<UIContent>>

    fun getFavouriteContentUIList(): Flow<UIList>
}
