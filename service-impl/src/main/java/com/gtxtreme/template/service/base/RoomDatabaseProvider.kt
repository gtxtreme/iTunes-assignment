package com.gtxtreme.template.service.base

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.gtxtreme.template.service.room.ItunesDatabase

fun getDatabaseProvider(applicationContext: Context): ItunesDatabase {
    return databaseBuilder(
        applicationContext,
        ItunesDatabase::class.java,
        "itunes_database"
    )
        .fallbackToDestructiveMigration()
        .build()
}
