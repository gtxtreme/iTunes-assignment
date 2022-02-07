package com.wednesday.template.interactor.weather.search

import app.cash.turbine.test
import com.wednesday.template.domain.base.Result
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCase
import com.wednesday.template.domain.weather.SearchCitiesUseCase
import com.wednesday.template.interactor.base.CoroutineContextController
import com.wednesday.template.interactor.base.InteractorTest
import com.wednesday.template.interactor.base.TestException
import com.wednesday.template.interactor.weather.search.models.city
import com.wednesday.template.interactor.weather.search.models.uiCity
import com.wednesday.template.presentation.base.UIList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.same
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class SearchCityInteractorImplTest : InteractorTest() {

    private lateinit var searchCitiesUseCase: SearchCitiesUseCase
    private lateinit var favouriteCitiesFlowUseCase: GetFavouriteCitiesFlowUseCase
    private lateinit var citySearchResultsMapper: UICitySearchResultsMapper
    private lateinit var coroutineContextController: CoroutineContextController
    private lateinit var interactor: SearchCityInteractorImpl

    @Before
    fun setUp() {
        searchCitiesUseCase = mock()
        favouriteCitiesFlowUseCase = mock()
        citySearchResultsMapper = mock()
        coroutineContextController = coroutineScopeRule.coroutineContextController
    }

    private fun verifyNoMoreInteractions() {
        verifyNoMoreInteractions(
            searchCitiesUseCase,
            citySearchResultsMapper,
            favouriteCitiesFlowUseCase
        )
    }

    private fun createInteractor() {
        // Usually interactor will be created just list any other object under test in the setup function
        // This interactor has 2 flows that are created as soon as the object is created.
        // For this reason we need to create the instance after mocking.
        interactor = SearchCityInteractorImpl(
            searchCitiesUseCase,
            favouriteCitiesFlowUseCase,
            citySearchResultsMapper,
            coroutineContextController
        )
    }

    @Test
    fun `Given no error occurs, When search called, Then search result flow emits UIList of results`(): Unit =
        coroutineScopeRule.runBlockingTest {
            // Given
            val searchTerm = "Pune"
            val uiList = UIList(uiCity)
            val cityList = listOf(city)
            whenever(searchCitiesUseCase(searchTerm)).thenReturn(Result.Success(cityList))
            whenever(favouriteCitiesFlowUseCase(Unit)).thenReturn(flowOf(cityList))
            whenever(citySearchResultsMapper.map(any(), any())).thenReturn(uiList)

            createInteractor()

            // When
            interactor.searchResultsFlow.test {
                interactor.search(searchTerm)

                val result = awaitItem()

                // Then
                assertEquals(expected = uiList, actual = result)
                verify(searchCitiesUseCase, times(1)).invoke(same(searchTerm))
                verify(citySearchResultsMapper, times(1)).map(same(cityList), same(cityList))
                verify(favouriteCitiesFlowUseCase, times(1)).invoke(Unit)
                verifyNoMoreInteractions()
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `Given search use case returns error, When search called, Then search result flow emits empty list`(): Unit =
        coroutineScopeRule.runBlockingTest {
            // Given
            val searchTerm = "Pune"
            val uiList = UIList()
            val cityList = listOf(city)
            val testException = TestException()
            whenever(searchCitiesUseCase(searchTerm)).thenReturn(Result.Error(testException))
            whenever(favouriteCitiesFlowUseCase(Unit)).thenReturn(flowOf(cityList))
            whenever(citySearchResultsMapper.map(any(), any())).thenReturn(uiList)

            createInteractor()

            // When
            interactor.searchResultsFlow.test {
                interactor.search(searchTerm)
                val result = awaitItem()

                // Then
                assertEquals(expected = uiList, actual = result)
                verify(searchCitiesUseCase, times(1)).invoke(same(searchTerm))
                verify(citySearchResultsMapper, times(1)).map(same(cityList), same(listOf()))
                verify(favouriteCitiesFlowUseCase, times(1)).invoke(Unit)
                verifyNoMoreInteractions()
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `Given mapper throws error, When search called, Then search result flow emits empty list`(): Unit =
        coroutineScopeRule.runBlockingTest {
            // Given
            val searchTerm = "Pune"
            val uiList = UIList()
            val cityList = listOf(city)
            val testException = TestException()
            whenever(searchCitiesUseCase(searchTerm)).thenReturn(Result.Success(cityList))
            whenever(favouriteCitiesFlowUseCase(Unit)).thenReturn(flowOf(cityList))
            whenever(citySearchResultsMapper.map(any(), any())).thenThrow(testException)

            createInteractor()

            // When
            interactor.searchResultsFlow.test {
                interactor.search(searchTerm)

                val result = awaitItem()

                // Then
                assertEquals(expected = uiList, actual = result)
                verify(searchCitiesUseCase, times(1)).invoke(same(searchTerm))
                verify(citySearchResultsMapper, times(1)).map(same(cityList), same(cityList))
                verify(favouriteCitiesFlowUseCase, times(1)).invoke(Unit)
                verifyNoMoreInteractions()
                cancelAndConsumeRemainingEvents()
            }
        }
}
