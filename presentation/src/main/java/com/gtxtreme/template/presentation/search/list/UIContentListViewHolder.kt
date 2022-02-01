package com.gtxtreme.template.presentation.search.list

import androidx.core.content.ContextCompat
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.list.viewholder.BaseViewHolder
import com.gtxtreme.template.presentation.content.UIContent
import com.gtxtreme.template.presentation.search.SearchScreenIntent
import com.gtxtreme.template.resources.databinding.ListItemContentBinding
import kotlinx.coroutines.channels.Channel

class UIContentListViewHolder(val binding: ListItemContentBinding) :
    BaseViewHolder<UIContent>(binding) {

    private val likedDrawable =
        ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite)
    private val unlikedDrawable =
        ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_border)

    override fun onSetupIntents(intentChannel: Channel<Intent>) {
        binding.imageViewFavourite.setOnClickListener {
            intentChannel.trySend(SearchScreenIntent.ToggleFavourite)
            binding.imageViewFavourite.setImageDrawable(likedDrawable)
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

            imageViewFavourite.setImageDrawable(unlikedDrawable)
        }
    }
}
