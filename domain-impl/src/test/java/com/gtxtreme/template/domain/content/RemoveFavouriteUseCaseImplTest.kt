package com.gtxtreme.template.domain.content

import com.gtxtreme.template.domain.base.Result
import com.gtxtreme.template.domain.content.models.testContent
import com.gtxtreme.template.repo.ContentRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

class RemoveFavouriteUseCaseImplTest {

    private lateinit var contentRepository: ContentRepository
    private lateinit var removeFavouriteUseCaseImpl: RemoveFavouriteUseCaseImpl

    @Before
    fun performSetup() {
        contentRepository = mock()
        removeFavouriteUseCaseImpl = RemoveFavouriteUseCaseImpl(contentRepository)
    }

    @Test
    fun `Given content, when remove as favourite, then return success`() {

        runBlocking {
            // Given
            whenever(contentRepository.unmarkContentAsFavourite(testContent))
                .thenReturn(Unit)
            // When
            val result = removeFavouriteUseCaseImpl(testContent)

            // Then
            assertTrue(result is Result.Success)
            verify(contentRepository, times(1)).unmarkContentAsFavourite(any())
            verifyNoMoreInteractions(contentRepository)
        }
    }

    @Test
    fun `Given content, when remove as favourite, then return error`() {

        runBlocking {
            // Given
            whenever(contentRepository.unmarkContentAsFavourite(testContent))
                .thenThrow(RuntimeException())

            // When
            val result = removeFavouriteUseCaseImpl(testContent)

            // Then
            assertTrue(result is Result.Error)
            verify(contentRepository, times(1)).unmarkContentAsFavourite(any())
            verifyNoMoreInteractions(contentRepository)
        }
    }
}
