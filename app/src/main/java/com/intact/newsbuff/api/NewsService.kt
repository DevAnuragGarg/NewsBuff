package com.intact.newsbuff.api

import com.intact.newsbuff.pojo.TrendingNewsResponse
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
        @Query("page") pageNumber: Int,
        @Query("country") country: String = "in"
    ): Call<TrendingNewsResponse>
}