package com.wednesday.template.presentation.weather.home

import androidx.lifecycle.viewModelScope // ktlint-disable import-ordering
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.navigation.home.HomeNavigator
import com.gtxtreme.template.presentation.R
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.weather.search.SearchScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val favouriteWeatherInteractor: FavouriteWeatherInteractor,
) : BaseViewModel<HomeScreen, HomeScreenState, HomeNavigator>(),
    IntentHandler<HomeScreenIntent> {

    override fun getDefaultScreenState(): HomeScreenState {
        return HomeScreenState(
            toolbar = UIToolbar(
                title = UIText { block("Weather") },
                hasBackButton = false,
                menuIcon = R.drawable.ic_search,
            ),
            showLoading = false,
            items = UIList()
        )
    }

    override fun onCreate(fromRecreate: Boolean) {
        if (fromRecreate) return

        favouriteWeatherInteractor.getFavouriteCitiesFlow().onEach {
            favouriteWeatherInteractor.fetchFavouriteCitiesWeather()
        }.launchIn(viewModelScope)

        favouriteWeatherInteractor.getFavouriteWeatherUIList().onEach {
            setState { copy(showLoading = false, items = it) }
        }.launchIn(viewModelScope)
    }

    override fun onIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.Search -> {
                navigator.navigateTo(SearchScreen)
            }
        }
    }
}
