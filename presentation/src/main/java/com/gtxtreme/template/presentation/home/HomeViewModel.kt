package com.gtxtreme.template.presentation.home

import com.gtxtreme.template.navigation.BaseNavigator // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.UIText
import com.gtxtreme.template.presentation.base.UIToolbar
import com.gtxtreme.template.presentation.base.intent.IntentHandler
import com.gtxtreme.template.presentation.base.viewmodel.BaseViewModel
import com.gtxtreme.template.presentation.search.SearchScreen
import timber.log.Timber

class HomeViewModel :
    BaseViewModel<HomeScreen, HomeScreenState, BaseNavigator>(), IntentHandler<HomeScreenIntent> {

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
                block("Tap the Search Icon to look for favourites")
            }
        )
    }

    override fun onIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.Search -> navigator.navigateTo(SearchScreen)
        }
    }

    override fun onCreate(fromRecreate: Boolean) {
        Timber.d("HomeViewModel was created")
    }
}
