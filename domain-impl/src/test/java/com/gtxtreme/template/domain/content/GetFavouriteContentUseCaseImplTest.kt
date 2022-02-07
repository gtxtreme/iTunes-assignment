package com.gtxtreme.template.domain.content

import app.cash.turbine.test
import com.gtxtreme.template.domain.content.models.testContent
import com.gtxtreme.template.repo.ContentRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

class GetFavouriteContentUseCaseImplTest {

    private lateinit var contentRepository: ContentRepository
    private lateinit var getFavouriteUseCase: GetFavouriteContentUseCaseImpl

    @Before
    fun performSetup() {
        contentRepository = mock()
        getFavouriteUseCase = GetFavouriteContentUseCaseImpl(contentRepository)
    }

    @ExperimentalTime
    @Test
    fun `Given getFavouriteContent is called Then a list of favourite content is returned`() {

        runBlocking {
            // Given
            val testListOfFavouriteContent =
                listOf(testContent)
            whenever(contentRepository.getFavoriteContentFlow())
                .thenReturn(flowOf(testListOfFavouriteContent))

            // When
            getFavouriteUseCase(Unit).test {
                val result = awaitItem()

                // Then
                assertEquals(testListOfFavouriteContent, result)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
