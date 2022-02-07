package com.gtxtreme.template.presentation.search

import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.content.UIContent

sealed interface SearchScreenIntent : Intent {
    class SearchContent(val artistName: String) : SearchScreenIntent
    class ToggleFavourite(val content: UIContent) : SearchScreenIntent
}
