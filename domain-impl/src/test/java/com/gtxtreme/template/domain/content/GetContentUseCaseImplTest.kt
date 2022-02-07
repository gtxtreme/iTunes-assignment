package com.gtxtreme.template.domain.content

import com.gtxtreme.template.domain.base.Result
import com.gtxtreme.template.repo.ContentRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import java.lang.RuntimeException
import kotlin.test.assertTrue

class GetContentUseCaseImplTest {

    private lateinit var contentRepository: ContentRepository
    private lateinit var getContentUseCase: GetContentUseCaseImpl

    @Before
    fun performSetup() {
        contentRepository = mock()
        getContentUseCase = GetContentUseCaseImpl(contentRepository)
    }

    private fun verifyNoMoreInteractionsInternal() {
        verifyNoMoreInteractions(contentRepository)
    }

    @Test
    fun `Given artistName When getAllContentByArtistName is called Then give all content `() {
        runBlocking {
            // GIVEN
            val artistName = "Arijit Singh"
            whenever(contentRepository.getAllContentByArtistName(artistName))
                .thenReturn(listOf())

            // WHEN
            val result = getContentUseCase(artistName)

            // THEN
            assertTrue(result is Result.Success)
            verify(contentRepository, times(1)).getAllContentByArtistName(artistName)
            verifyNoMoreInteractionsInternal()
        }
    }

    @Test
    fun `Given artistName When getAllContentByArtistName is called then throw an exception`() {
        runBlocking {
            // GIVEN

            val artistName = "Arijit Singh"
            whenever(contentRepository.getAllContentByArtistName(artistName))
                .thenThrow(
                    RuntimeException()
                )

            // WHEN

            val result = getContentUseCase(artistName)

            // THEN
            assertTrue(result is Result.Error)
            verify(contentRepository, times(1)).getAllContentByArtistName(artistName)
            verifyNoMoreInteractionsInternal()
        }
    }
}
