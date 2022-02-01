package com.gtxtreme.template.presentation.search

import androidx.lifecycle.viewModelScope
import com.gtxtreme.template.interactor.content.GetContentInteractor
import com.gtxtreme.template.navigation.BaseNavigator // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.UIList
import com.gtxtreme.template.presentation.base.UIText
import com.gtxtreme.template.presentation.base.UIToolbar
import com.gtxtreme.template.presentation.base.effect.ShowSnackbarEffect
import com.gtxtreme.template.presentation.base.intent.IntentHandler
import com.gtxtreme.template.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(private val getContentInteractor: GetContentInteractor) :
    BaseViewModel<SearchScreen, SearchScreenState, BaseNavigator>(),
    IntentHandler<SearchScreenIntent> {

    private val getContentResponseFlow: MutableStateFlow<String> = MutableStateFlow("")

    override fun getDefaultScreenState(): SearchScreenState {
        return SearchScreenState(
            UIToolbar(
                title = UIText {
                    block("Search")
                },
                hasBackButton = true,
                menuIcon = R.drawable.ic_search
            ),
            UIList(),
            isLoading = false
        )
    }

    @FlowPreview
    override fun onCreate(fromRecreate: Boolean) {
        Timber.d("SearchViewModel was created")
        getContentInteractor.searchResults.onEach {
            setState {
                copy(
                    isLoading = false,
                    toolbar = UIToolbar(
                        title = UIText {
                            block("Searching..")
                        },
                        hasBackButton = true,
                        menuIcon = R.drawable.ic_search
                    ),
                    list = it
                )
            }
        }.launchIn(viewModelScope)

        getContentResponseFlow
            .debounce(500)
            .onEach { artistName ->

                if (artistName.isBlank()) {
                    setState { copy(list = UIList()) }
                    return@onEach
                }
                setState {
                    copy(isLoading = true, list = UIList())
                }
                getContentInteractor.search(artistName)
            }.launchIn(viewModelScope)
    }

    override fun onIntent(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.SearchContent -> {
                Timber.d("Artist to search: ${intent.artistName}")
                viewModelScope.launch {
                    getContentResponseFlow.value = intent.artistName
                }
            }
            is SearchScreenIntent.ToggleFavourite -> {
                setEffect(
                    ShowSnackbarEffect(
                        message = UIText {
                            block("Favourite Icon Pressed")
                        }
                    )
                )
            }
        }
    }
}
