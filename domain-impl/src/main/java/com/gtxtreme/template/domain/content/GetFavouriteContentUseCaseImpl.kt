package com.gtxtreme.template.domain.content

import com.gtxtreme.template.repo.ContentRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteContentUseCaseImpl(private val contentRepository: ContentRepository) :
    GetFavouriteContentUseCase {
    override fun invokeInternal(param: Unit): Flow<List<Content>> =
        contentRepository.getFavoriteContentFlow()
}
