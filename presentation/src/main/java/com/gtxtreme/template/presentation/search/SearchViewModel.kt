package com.gtxtreme.template.presentation.search

import com.gtxtreme.template.navigation.BaseNavigator // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.UIText
import com.gtxtreme.template.presentation.base.UIToolbar
import com.gtxtreme.template.presentation.base.intent.IntentHandler
import com.gtxtreme.template.presentation.base.viewmodel.BaseViewModel
import timber.log.Timber

class SearchViewModel : BaseViewModel<SearchScreen, SearchScreenState, BaseNavigator>(), IntentHandler<SearchScreenIntent> {
    override fun getDefaultScreenState(): SearchScreenState {
        // TODO add Default State for Search
        return SearchScreenState(
            UIToolbar(
                title = UIText {
                    block("Search")
                },
                hasBackButton = true,
                menuIcon = R.drawable.ic_search
            )
        )
    }

    override fun onCreate(fromRecreate: Boolean) {
        // TODO Add implementation for this
        Timber.d("SearchViewModel was created")
    }

    override fun onIntent(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.SearchContent -> {
                Timber.d("Artist to search: ${intent.artistName}")
            }
        }
    }
}
