package com.gtxtreme.template.service.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gtxtreme.template.service.content.FavouriteContent
import com.gtxtreme.template.service.content.MediaContentLocalService

@Database(entities = [FavouriteContent::class], version = 2)
abstract class ItunesDatabase : RoomDatabase() {
    abstract fun databaseDao(): MediaContentLocalService
}
