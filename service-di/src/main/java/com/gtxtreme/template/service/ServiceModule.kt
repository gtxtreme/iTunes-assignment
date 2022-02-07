package com.gtxtreme.template.service

import com.gtxtreme.template.service.base.getDatabaseProvider
import com.gtxtreme.template.service.base.getRetrofit
import com.gtxtreme.template.service.content.MediaContentLocalService
import com.gtxtreme.template.service.itunecontent.MediaContentRemoteService
import com.gtxtreme.template.service.room.ItunesDatabase
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    // Retrofit
    single {
        getRetrofit(get())
    }

    single {
        getDatabaseProvider(get())
    }

    single {
        getMediaContentService(get())
    }

    single {
        getMediaContentLocalService(get())
    }
}

fun getMediaContentLocalService(database: ItunesDatabase): MediaContentLocalService {
    return database.databaseDao()
}

fun getMediaContentService(retrofit: Retrofit): MediaContentRemoteService =
    retrofit.create(MediaContentRemoteService::class.java)
