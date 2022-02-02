package com.gtxtreme.template.presentation.home.list

import android.view.ViewGroup
import com.gtxtreme.template.presentation.base.list.renderer.ListItemRenderer
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.gtxtreme.template.presentation.content.UIContent
import com.gtxtreme.template.resources.databinding.ListItemContentBinding

class UIContentFavouriteListRenderer : ListItemRenderer<UIContent>() {
    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIContent> {
        return UIContentFavouriteListViewHolder(
            viewGroup bind ListItemContentBinding::inflate
        )
    }
}
