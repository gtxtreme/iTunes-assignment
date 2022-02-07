package com.gtxtreme.template.presentation.content

import com.gtxtreme.template.presentation.base.UIListItemBase
import com.gtxtreme.template.presentation.base.UIText //
import kotlinx.parcelize.Parcelize

@Parcelize
data class UISearchContentPlaceholder(val text: UIText) :
    UIListItemBase("UISearchContentPlaceholder")
