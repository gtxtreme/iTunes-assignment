package com.wednesday.template.presentation.weather.home.list

import com.wednesday.template.presentation.base.extensions.setUIText // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIDayWeatherHeading
import com.gtxtreme.template.resources.databinding.ItemDayWeatherHeadingBinding
import kotlinx.coroutines.channels.Channel

class UIDayWeatherHeadingViewHolder(
    private val binding: ItemDayWeatherHeadingBinding
) : BaseViewHolder<UIDayWeatherHeading>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) = Unit

    override fun onBindInternal() {

        compareAndSet({ text }) {
            binding.textViewHeading.setUIText(it)
        }
    }
}
