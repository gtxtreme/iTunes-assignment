package com.wednesday.template.presentation.weather.home.list

import android.view.ViewGroup // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.base.list.renderer.ListItemRenderer
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIDayWeather
import com.gtxtreme.template.resources.databinding.ItemDayWeatherBinding

class UIDayWeatherRenderer : ListItemRenderer<UIDayWeather>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIDayWeather> {
        return UIDayWeatherViewHolder(
            binding = viewGroup bind ItemDayWeatherBinding::inflate
        )
    }
}
