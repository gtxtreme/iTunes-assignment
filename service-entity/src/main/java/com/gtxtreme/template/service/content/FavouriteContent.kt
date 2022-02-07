package com.gtxtreme.template.service.content

import androidx.room.Entity
import androidx.room.PrimaryKey

// Model class to store the content that is favourite by the user
@Entity(tableName = "favorite_content")
data class FavouriteContent(
    @PrimaryKey
    val artistId: Int,
    val artistName: String,
    val trackName: String,
    val trackId: Int,
)
