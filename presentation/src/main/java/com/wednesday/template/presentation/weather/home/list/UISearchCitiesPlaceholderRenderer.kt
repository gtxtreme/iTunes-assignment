package com.wednesday.template.presentation.weather.home.list

import android.view.ViewGroup // ktlint-disable import-ordering
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.gtxtreme.template.resources.databinding.ItemSearchCityPlaceholderBinding

class UISearchCitiesPlaceholderRenderer : ListItemRenderer<UISearchCitiesPlaceholder>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UISearchCitiesPlaceholder> {
        return UISearchCitiesPlaceholderViewHolder(
            binding = viewGroup bind ItemSearchCityPlaceholderBinding::inflate
        )
    }
}
