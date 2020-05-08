package com.intact.newsbuff.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * All the requests related to news are written here
 */

interface NewsService {

    @GET("top-headlines/")
    fun getTopHeadlineNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Call<Any>
}