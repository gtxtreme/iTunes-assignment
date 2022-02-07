package com.gtxtreme.template.presentation.base.list.renderer

import android.view.ViewGroup
import com.gtxtreme.template.presentation.base.list.UIListText
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.gtxtreme.template.presentation.base.list.viewholder.UIListTextViewHolder
import com.gtxtreme.template.resources.databinding.ItemListTextBinding

class UIListTextRenderer : ListItemRenderer<UIListText>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIListText> {
        return UIListTextViewHolder(
            binding = viewGroup bind ItemListTextBinding::inflate
        )
    }
}
