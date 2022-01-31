package com.wednesday.template.presentation.base.list.renderer

import android.view.LayoutInflater // ktlint-disable import-ordering
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.gtxtreme.template.presentation.base.list.renderer.Inflater
import com.gtxtreme.template.presentation.base.list.renderer.ItemRenderer
import com.wednesday.template.presentation.base.UIListItemBase
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder

typealias Inflater<T> = (inflater: LayoutInflater, viewGroup: ViewGroup, attachToParent: Boolean) -> T

abstract class ListItemRenderer<T : UIListItemBase> : ItemRenderer<T> {

    abstract fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<T>

    protected infix fun <VB : ViewBinding> ViewGroup.bind(inflater: Inflater<VB>): VB {
        val layoutInflater = LayoutInflater.from(context)
        return inflater(layoutInflater, this, false)
    }
}
