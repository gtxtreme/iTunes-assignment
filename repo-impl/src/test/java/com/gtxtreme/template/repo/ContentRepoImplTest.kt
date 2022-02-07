package com.gtxtreme.template.repo

import app.cash.turbine.test
import com.gtxtreme.template.content.DomainContentMapper
import com.gtxtreme.template.repo.models.testContent
import com.gtxtreme.template.repo.models.testFavouriteContent
import com.gtxtreme.template.repo.models.testRemoteContent
import com.gtxtreme.template.repo.models.testRemoteResult
import com.gtxtreme.template.service.content.MediaContentLocalService
import com.gtxtreme.template.service.itunecontent.MediaContentRemoteService
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.verifyZeroInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime

class ContentRepoImplTest {

    private lateinit var mediaContentLocalService: MediaContentLocalService
    private lateinit var mediaContentRemoteService: MediaContentRemoteService
    private lateinit var domainContentMapper: DomainContentMapper
    private lateinit var contentRepository: ContentRepository

    @Before
    fun performSetup() {
        mediaContentRemoteService = mock()
        mediaContentLocalService = mock()
        domainContentMapper = mock()
        contentRepository = ContentRepoImpl(
            mediaContentRemoteService,
            mediaContentLocalService,
            domainContentMapper
        )
    }

    private fun verifyNoMoreInteractionsInternal() {
        verifyNoMoreInteractions(
            mediaContentLocalService,
            mediaContentRemoteService,
            domainContentMapper,
        )
    }

    @Test
    fun `Given artist name When getAllContentByArtistName is called Then provide content`() {
        runBlocking {
            val artistName = "Arijit Singh"
            val testContentResults = listOf(testContent)
            whenever(mediaContentRemoteService.getMediaDetailByAuthor(artistName))
                .thenReturn(testRemoteResult)
            whenever(domainContentMapper.map(listOf(testRemoteContent)))
                .thenReturn(testContentResults)

            // When
            val results = contentRepository.getAllContentByArtistName(artistName)
            // Then
            assertEquals(expected = testContentResults, actual = results)
            verify(mediaContentRemoteService, times(1)).getMediaDetailByAuthor(artistName)
            verify(domainContentMapper, times(1)).map(listOf(testRemoteContent))
            verifyZeroInteractions(mediaContentRemoteService)
        }
    }

    @Test
    fun `Given artist name When getAllContentByArtistName is called Then check if empty list`() {
        runBlocking {
            // GIVEN
            val artistName = "Arijit Singh"
            whenever(mediaContentRemoteService.getMediaDetailByAuthor(any()))
                .thenReturn(testRemoteResult.copy(0))

            // WHEN
            val results = contentRepository.getAllContentByArtistName(artistName)

            // THEN
            assertTrue(results.isEmpty())
            verify(mediaContentRemoteService, times(1)).getMediaDetailByAuthor(any())
            verifyNoMoreInteractionsInternal()
        }
    }

    @Test
    fun `Given markContentAsFavourite Then mark a given content as favourite`() {

        runBlocking {
            val content = testContent
            val favouriteContent = testFavouriteContent
            whenever(domainContentMapper.mapContent(content))
                .thenReturn(favouriteContent)

            contentRepository.markContentAsFavourite(content)

            verify(mediaContentLocalService, times(1)).markContentAsFavourite(any())
            verify(domainContentMapper, times(1)).mapContent(any())
            verifyNoMoreInteractionsInternal()
        }
    }

    @Test
    fun `Given removeContentAsFavourite Then remove content as favourite`() {
        runBlocking {
            val content = testContent
            val favouriteContent = testFavouriteContent
            whenever(domainContentMapper.mapContent(content))
                .thenReturn(favouriteContent)

            contentRepository.unmarkContentAsFavourite(content)

            verify(mediaContentLocalService, times(1)).removeContentAsFavourite(any())
            verify(domainContentMapper, times(1)).mapContent(any())
            verifyNoMoreInteractionsInternal()
        }
    }

    @ExperimentalTime
    @Test
    fun `Given getFavouriteContentFlow Then return favourite content`() {
        // Given

        runBlocking {
            val testContentList = listOf(testContent)
            val testFavouriteContentList = listOf(testFavouriteContent)
            whenever(mediaContentLocalService.getAllFavouriteContentFlow())
                .thenReturn(flowOf(testFavouriteContentList))
            whenever(domainContentMapper.mapFavouriteContentList(testFavouriteContentList))
                .thenReturn(testContentList)

            // When
            contentRepository.getFavoriteContentFlow().test {
                val resultList = awaitItem()

                assertEquals(testContentList, resultList)
                verify(mediaContentLocalService, times(1)).getAllFavouriteContentFlow()
                verify(domainContentMapper, times(1)).mapFavouriteContentList(any())
                verifyNoMoreInteractionsInternal()
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
