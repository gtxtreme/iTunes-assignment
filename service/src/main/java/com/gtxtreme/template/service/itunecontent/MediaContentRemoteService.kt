package com.gtxtreme.template.service.itunecontent

import com.gtxtreme.template.service.content.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaContentRemoteService {

    @GET("search")
    suspend fun getMediaDetailByAuthor(@Query("term", encoded = true) authorName: String): RemoteResult


}
