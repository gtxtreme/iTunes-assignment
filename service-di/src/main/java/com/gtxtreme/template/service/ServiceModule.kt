package com.gtxtreme.template.service

import com.gtxtreme.template.service.base.getRetrofit
import com.gtxtreme.template.service.itunecontent.MediaContentRemoteService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {


    //Retrofit
    single {
        getRetrofit(get())
    }

    //TODO Room

    single {
        getMediaContentService(get())
    }
}


fun getMediaContentService(retrofit: Retrofit): MediaContentRemoteService = retrofit.create(MediaContentRemoteService::class.java)
