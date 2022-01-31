package com.gtxtreme.template.presentation.home

import com.gtxtreme.template.presentation.screen.MainScreenState // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.base.UIText
import com.gtxtreme.template.presentation.base.UIToolbar
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeScreenState(
    override val toolbar: UIToolbar,
    val text: UIText
) : MainScreenState
