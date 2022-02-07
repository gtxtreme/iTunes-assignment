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

class MarkAsFavouriteUseCaseImplTest {

    private lateinit var contentRepository: ContentRepository
    private lateinit var markAsFavouriteUseCaseImpl: MarkAsFavouriteUseCaseImpl

    @Before
    fun performSetup() {
        contentRepository = mock()
        markAsFavouriteUseCaseImpl = MarkAsFavouriteUseCaseImpl(contentRepository)
    }

    @Test
    fun `Given content When mark it as favourite Then return Success`() {

        runBlocking {
            // GIVEN
            whenever(contentRepository.markContentAsFavourite(testContent))
                .thenReturn(Unit)

            // WHEN
            val result = markAsFavouriteUseCaseImpl(testContent)

            // THEN
            assertTrue(result is Result.Success)
            verify(contentRepository, times(1)).markContentAsFavourite(testContent)
            verifyNoMoreInteractions(contentRepository)
        }
    }

    @Test
    fun `Given content when mark it as favourite then return failure`() {
        runBlocking {
            // Given
            whenever(contentRepository.markContentAsFavourite(testContent))
                .thenThrow(RuntimeException())

            // When
            val result = markAsFavouriteUseCaseImpl(testContent)

            // Then
            assertTrue(result is Result.Error)
            verify(contentRepository, times(1)).markContentAsFavourite(any())
            verifyNoMoreInteractions(contentRepository)
        }
    }
}
