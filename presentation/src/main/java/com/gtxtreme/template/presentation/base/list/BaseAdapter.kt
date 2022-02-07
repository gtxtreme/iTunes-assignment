package com.gtxtreme.template.presentation.base.list

import com.gtxtreme.template.presentation.base.UIListItemBase

interface BaseAdapter {

    val items: List<UIListItemBase>

    fun updateData(newItems: List<UIListItemBase>)
}
