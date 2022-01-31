package com.wednesday.template.presentation.weather.home.list

import com.wednesday.template.presentation.base.extensions.setUIText // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.presentation.weather.home.HomeScreenIntent
import com.gtxtreme.template.resources.databinding.ItemSearchCityPlaceholderBinding
import kotlinx.coroutines.channels.Channel

class UISearchCitiesPlaceholderViewHolder(private val binding: ItemSearchCityPlaceholderBinding) :
    BaseViewHolder<UISearchCitiesPlaceholder>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) = binding.run {
        buttonSearch.setOnClickListener {
            intentChannel.trySend(HomeScreenIntent.Search)
        }
    }

    override fun onBindInternal() = binding.run {

        compareAndSet({ text }) {
            textViewPlaceholder.setUIText(it)
        }
    }
}
