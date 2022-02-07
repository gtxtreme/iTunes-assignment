package com.gtxtreme.template.interactor.content

import app.cash.turbine.test
import com.gtxtreme.template.domain.base.Result
import com.gtxtreme.template.domain.content.GetContentUseCase
import com.gtxtreme.template.domain.content.GetFavouriteContentUseCase
import com.gtxtreme.template.interactor.base.CoroutineContextController
import com.gtxtreme.template.interactor.base.InteractorTest
import com.gtxtreme.template.interactor.content.models.testContent
import com.gtxtreme.template.interactor.content.models.testUiContent
import com.gtxtreme.template.presentation.base.UIList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class GetContentInteractorTest : InteractorTest() {

    private lateinit var getContentUseCase: GetContentUseCase
    private lateinit var coroutineContextController: CoroutineContextController
    private lateinit var getFavouriteContentUseCase: GetFavouriteContentUseCase
    private lateinit var uiContentListResultsMapper: UIContentListResultsMapper
    private lateinit var getContentInteractor: GetContentInteractorImpl

    private fun initInteractor() {
        getContentInteractor = GetContentInteractorImpl(
            getContentUseCase,
            coroutineContextController,
            getFavouriteContentUseCase,
            uiContentListResultsMapper
        )
    }

    private fun verifyNoOtherInteractions() {
        verifyNoMoreInteractions(
            getContentUseCase,
            getFavouriteContentUseCase,
            uiContentListResultsMapper
        )
    }

    @Before
    fun performSetup() {
        getContentUseCase = mock()
        coroutineContextController = coroutineScopeRule.coroutineContextController
        getFavouriteContentUseCase = mock()
        uiContentListResultsMapper = mock()
    }

    @ExperimentalTime
    @Test
    fun `Given a search artistName, When perform search , Then return a list of content`() {

        coroutineScopeRule.runBlockingTest {
            // Given
            val artistName = "Arijit Singh"
            val testContentList = listOf(testContent)
            val uiList = UIList(listOf(testUiContent))
            whenever(getContentUseCase(artistName)).thenReturn(Result.Success(testContentList))
            whenever(getFavouriteContentUseCase(Unit)).thenReturn(flowOf(testContentList))
            whenever(uiContentListResultsMapper.map(any(), any())).thenReturn(uiList)

            initInteractor()

            getContentInteractor.searchResults.test {
                getContentInteractor.search(param = artistName)
                val result = awaitItem()
                assertEquals(uiList, result)
                verify(getContentUseCase, times(1)).invoke(artistName)
                verify(getFavouriteContentUseCase, times(1)).invoke(Unit)
                verify(uiContentListResultsMapper, times(1)).map(any(), any())
            }
        }
    }
}
