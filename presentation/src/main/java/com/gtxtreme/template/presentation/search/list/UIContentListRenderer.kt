package com.gtxtreme.template.presentation.search.list

import android.view.ViewGroup
import com.gtxtreme.template.presentation.base.list.renderer.ListItemRenderer
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.gtxtreme.template.presentation.content.UIContent
import com.gtxtreme.template.resources.databinding.ListItemContentBinding

class UIContentListRenderer : ListItemRenderer<UIContent>() {
    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIContent> {
        return UIContentListViewHolder(
            viewGroup bind ListItemContentBinding::inflate
        )
    }
}
