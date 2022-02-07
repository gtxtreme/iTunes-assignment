package com.gtxtreme.template.domain.content

import com.gtxtreme.template.repo.ContentRepository

class MarkAsFavouriteUseCaseImpl(private val contentRepository: ContentRepository) : MarkAsFavouriteUseCase {
    override suspend fun invokeInternal(content: Content) {
        contentRepository.markContentAsFavourite(content)
    }
}
