package com.gtxtreme.template.presentation.content

import com.gtxtreme.template.presentation.base.UIListItemBase
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIContent(
    val artistId: Int,
    val artistName: String,
    val trackName: String,
    val trackId: Int,
    val isFavourite: Boolean
) : UIListItemBase("UIContent$artistId")
