package com.gtxtreme.template.presentation.base.list.renderer

import android.view.ViewGroup
import com.gtxtreme.template.presentation.base.list.UIListUIText
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.gtxtreme.template.presentation.base.list.viewholder.UIListUITextViewHolder
import com.gtxtreme.template.resources.databinding.ItemListTextBinding

class UIListUITextRenderer : ListItemRenderer<UIListUIText>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIListUIText> {
        return UIListUITextViewHolder(
            binding = viewGroup bind ItemListTextBinding::inflate
        )
    }
}
