package com.gtxtreme.template.presentation.home

import com.gtxtreme.template.interactor.content.FavouriteContentInteractor
import com.gtxtreme.template.navigation.BaseNavigator
import com.gtxtreme.template.presentation.base.UIList
import com.gtxtreme.template.presentation.base.UIText
import com.gtxtreme.template.presentation.base.UIToolbar
import com.gtxtreme.template.presentation.content.UIContent
import com.gtxtreme.template.presentation.home.base.BaseViewModelTest
import com.gtxtreme.template.presentation.search.SearchScreen
import com.gtxtreme.template.resources.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseViewModelTest() {

    private lateinit var getFavouriteContentInteractor: FavouriteContentInteractor
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var baseNavigator: BaseNavigator

    override fun before() {
        getFavouriteContentInteractor = mock()
        baseNavigator = mock()
        homeViewModel = HomeViewModel(getFavouriteContentInteractor)
    }

    override fun after() = Unit

    @Test
    fun `Given _ when state is Search then go to search screen`() {
        // Given
        whenever(getFavouriteContentInteractor.getFavouriteContentUIList())
            .thenReturn(flowOf())
        homeViewModel.onCreate(null, baseNavigator)
        // When
        homeViewModel.onIntent(HomeScreenIntent.Search)

        // Then
        verify(baseNavigator, times(1)).navigateTo(SearchScreen)
    }

    @Test
    fun `Given _ when state is Remove Favourite then remove the favourite from the list`() {
        // Given
        val testUiContent = UIContent(1, "Arijit Singh", "Bekhayali", 1234, true)
        val initialState = HomeScreenState(
            toolbar = UIToolbar(
                title = UIText {
                    block("Itunes API")
                },
                hasBackButton = false,
                menuIcon = R.drawable.ic_search
            ),
            text = UIText {
                block(R.string.tap_on_search)
            }
        )
        val testUIList = UIList(testUiContent)
        runBlocking {
            // Given
            whenever(getFavouriteContentInteractor.removeContentAsFavourite(testUiContent))
                .thenReturn(Unit)

            // When
            homeViewModel.onIntent(HomeScreenIntent.RemoveFavourite(testUiContent))

            // Then
            val observer = mockObserver<HomeScreenState>()
            verify(observer).onChanged(initialState.copy(items = testUIList))
        }
    }
}
