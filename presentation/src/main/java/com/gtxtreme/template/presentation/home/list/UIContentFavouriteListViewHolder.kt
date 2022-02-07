package com.gtxtreme.template.presentation.home.list

import androidx.core.content.ContextCompat
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.gtxtreme.template.presentation.content.UIContent
import com.gtxtreme.template.presentation.home.HomeScreenIntent
import com.gtxtreme.template.resources.databinding.ListItemContentBinding
import kotlinx.coroutines.channels.Channel

class UIContentFavouriteListViewHolder(val binding: ListItemContentBinding) : BaseViewHolder<UIContent>(binding) {
    override fun onSetupIntents(intentChannel: Channel<Intent>) {
        binding.imageViewFavourite.setOnClickListener {
            intentChannel.trySend(HomeScreenIntent.RemoveFavourite(item))
        }
    }

    override fun onBindInternal() {
        binding.run {
            compareAndSet({ artistName }) {
                textArtistName.text = it
            }

            compareAndSet({ trackName }) {
                textTrackName.text = it
            }

            compareAndSet({ isFavourite }) {
                val drawable = ContextCompat.getDrawable(
                    root.context,
                    if (it) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                )
                imageViewFavourite.setImageDrawable(drawable)
            }
        }
    }
}
