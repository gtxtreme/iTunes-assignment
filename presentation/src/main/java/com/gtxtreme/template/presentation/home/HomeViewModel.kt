package com.gtxtreme.template.presentation.home

import androidx.lifecycle.viewModelScope
import com.gtxtreme.template.interactor.content.FavouriteContentInteractor
import com.gtxtreme.template.navigation.BaseNavigator
import com.gtxtreme.template.presentation.base.UIText
import com.gtxtreme.template.presentation.base.UIToolbar
import com.gtxtreme.template.presentation.base.intent.IntentHandler
import com.gtxtreme.template.presentation.base.viewmodel.BaseViewModel
import com.gtxtreme.template.presentation.search.SearchScreen
import com.gtxtreme.template.resources.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val favouriteContentInteractor: FavouriteContentInteractor
) : BaseViewModel<HomeScreen, HomeScreenState, BaseNavigator>(), IntentHandler<HomeScreenIntent> {

    override fun getDefaultScreenState(): HomeScreenState {
        return HomeScreenState(
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
    }

    override fun onIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.Search -> navigator.navigateTo(SearchScreen)
            is HomeScreenIntent.RemoveFavourite -> {
                viewModelScope.launch {
                    favouriteContentInteractor.removeContentAsFavourite(intent.item)
                }
            }
        }
    }

    override fun onCreate(fromRecreate: Boolean) {
        // Do not refresh the list or your state when the view is recreated after configChanges or whatever changes
        if (fromRecreate) return
        favouriteContentInteractor.getFavouriteContentUIList().onEach {
            setState {
                copy(items = it)
            }
        }.launchIn(viewModelScope)
    }
}
