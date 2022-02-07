package com.gtxtreme.template.presentation.base.list

import com.gtxtreme.template.presentation.base.UIListItemBase
import com.gtxtreme.template.presentation.base.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIListUIText(
    val text: UIText
) : UIListItemBase(id = "UIListUIText $text")
