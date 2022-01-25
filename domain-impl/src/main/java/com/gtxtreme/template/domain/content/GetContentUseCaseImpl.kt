package com.gtxtreme.template.domain.content

import com.gtxtreme.template.repo.ContentRepository
import timber.log.Timber

class GetContentUseCaseImpl(private val contentRepository: ContentRepository): GetContentUseCase {

    companion object {
        val TAG = "GetContentUseCaseImpl"
    }

    override suspend fun invokeInternal(param: String): List<Content> {
        Timber.tag(TAG).d("invokeInternal() called with: param = $param")
        return contentRepository.getAllContentByArtistName(param)
    }
}