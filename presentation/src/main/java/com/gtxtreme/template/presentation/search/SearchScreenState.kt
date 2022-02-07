package com.gtxtreme.template.presentation.search

import com.gtxtreme.template.presentation.base.UIList
import com.gtxtreme.template.presentation.base.UIToolbar
import com.gtxtreme.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchScreenState(
    override val toolbar: UIToolbar,
    val list: UIList,
    override val isLoading: Boolean
) : MainScreenState
