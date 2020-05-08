package com.intact.newsbuff.api

import com.intact.newsbuff.API_KEY
import com.intact.newsbuff.BuildConfig
import com.intact.newsbuff.util.BASE_URL
import com.intact.newsbuff.util.CONNECTION_TIME_OUT
import com.intact.newsbuff.util.READ_TIME_OUT
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NewsRepository private constructor() {

    private val newsService: NewsService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        // creating okhttp client
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(APIInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()

        // creating retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Well in order for our
            // calls to return type Observable, we have to set the call adapter to RxJavaCallAdapter.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // creating service
        newsService = retrofit.create(NewsService::class.java)
    }

    companion object {

        private var newsRepository: NewsRepository? = null

        fun getInstance(): NewsRepository {

            synchronized(this) {
                if (newsRepository == null) {
                    newsRepository = NewsRepository()
                }
            }
            return newsRepository!!
        }
    }

    fun getTrendingNews() = newsService.getTopHeadlineNews(API_KEY)
}