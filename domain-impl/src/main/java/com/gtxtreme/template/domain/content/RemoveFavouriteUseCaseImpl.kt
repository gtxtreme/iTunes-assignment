package com.gtxtreme.template.domain.content

import com.gtxtreme.template.repo.ContentRepository

class RemoveFavouriteUseCaseImpl(private val contentRepository: ContentRepository) : RemoveFavouriteUseCase {
    override suspend fun invokeInternal(param: Content) {
        contentRepository.unmarkContentAsFavourite(param)
    }
}
