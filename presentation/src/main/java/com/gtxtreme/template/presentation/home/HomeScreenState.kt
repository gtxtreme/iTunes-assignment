package com.gtxtreme.template.presentation.home

import com.gtxtreme.template.presentation.base.UIList
import com.gtxtreme.template.presentation.base.UIText
import com.gtxtreme.template.presentation.base.UIToolbar
import com.gtxtreme.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeScreenState(
    override val toolbar: UIToolbar,
    val text: UIText,
    override val isLoading: Boolean = false,
    val items: UIList = UIList()
) : MainScreenState
