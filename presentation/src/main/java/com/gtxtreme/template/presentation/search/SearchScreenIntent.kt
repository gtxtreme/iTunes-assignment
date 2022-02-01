package com.gtxtreme.template.presentation.search

import com.gtxtreme.template.presentation.base.intent.Intent

sealed interface SearchScreenIntent : Intent {
    class SearchContent(val artistName: String) : SearchScreenIntent
    object ToggleFavourite : SearchScreenIntent
}
