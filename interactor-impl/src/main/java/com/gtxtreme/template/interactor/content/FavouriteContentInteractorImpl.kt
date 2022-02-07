package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.domain.content.GetFavouriteContentUseCase
import com.gtxtreme.template.domain.content.MarkAsFavouriteUseCase
import com.gtxtreme.template.domain.content.RemoveFavouriteUseCase
import com.gtxtreme.template.interactor.UIContentMapper
import com.gtxtreme.template.interactor.base.CoroutineContextController
import com.gtxtreme.template.presentation.base.UIList
import com.gtxtreme.template.presentation.content.UIContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class FavouriteContentInteractorImpl(
    private val markAsFavouriteUseCase: MarkAsFavouriteUseCase,
    private val getFavouriteContentUseCase: GetFavouriteContentUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
    private val uiContentMapper: UIContentMapper,
    private val coroutineContextController: CoroutineContextController,
) : FavouriteContentInteractor {
    override suspend fun markContentAsFavourite(content: UIContent) {
        Timber.d("Added Favourite:${content.trackName}")
        coroutineContextController.switchToDefault {
            markAsFavouriteUseCase(uiContentMapper.mapContent(content))
        }
    }

    override suspend fun removeContentAsFavourite(content: UIContent) {
        Timber.d("Removed Favourite: ${content.trackName}")
        coroutineContextController.switchToDefault {
            removeFavouriteUseCase(uiContentMapper.mapContent(content))
        }
    }

    override fun getFavouriteContentUIList(): Flow<UIList> {
        return getFavouriteContentUseCase(Unit).map {
            UIList(uiContentMapper.mapFavouriteContent(it))
        }
    }
}
