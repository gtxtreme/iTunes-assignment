package com.gtxtreme.template.service.itunecontent

import retrofit2.http.GET
import retrofit2.http.Query

interface MediaContentRemoteService {

    @GET("search")
    fun getMediaDetailByAuthor(@Query("term", encoded = true) authorName: String)


}