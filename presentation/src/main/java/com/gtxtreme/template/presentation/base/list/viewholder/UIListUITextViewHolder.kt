package com.gtxtreme.template.presentation.base.list.viewholder

import com.gtxtreme.template.presentation.base.extensions.asString // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.list.UIListUIText
import com.gtxtreme.template.presentation.base.extensions.asString // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.list.UIListUIText
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.gtxtreme.template.resources.databinding.ItemListTextBinding
import kotlinx.coroutines.channels.Channel

class UIListUITextViewHolder(private val binding: ItemListTextBinding) :
    BaseViewHolder<UIListUIText>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) {
        /* no-op */
    }

    override fun onBindInternal() = binding.run {
        compareAndSet({ text }) {
            textViewListItem.text = it.asString()
        }
    }
}
