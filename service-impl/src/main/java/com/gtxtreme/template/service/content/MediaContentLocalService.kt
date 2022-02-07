package com.gtxtreme.template.service.content

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaContentLocalService {

    @Query("SELECT * FROM favorite_content")
    fun getAllFavouriteContentFlow(): Flow<List<FavouriteContent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markContentAsFavourite(favouriteContent: FavouriteContent)

    @Delete
    suspend fun removeContentAsFavourite(favouriteContent: FavouriteContent)
}
