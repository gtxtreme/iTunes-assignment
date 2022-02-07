package com.gtxtreme.template.presentation.base.effect

import android.view.View
import com.gtxtreme.template.presentation.base.UIText

interface Effect

data class ShowSnackbarEffect(
    val message: UIText,
    val action: UIText? = null,
    val onActionClick: (View) -> Unit = {}
) : Effect

object HideKeyboardEffect : Effect
