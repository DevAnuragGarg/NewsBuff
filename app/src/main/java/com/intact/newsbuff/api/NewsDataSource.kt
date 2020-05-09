package com.intact.newsbuff.api

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.intact.newsbuff.pojo.NewsDTO
import com.intact.newsbuff.pojo.TrendingNewsResponse
import com.intact.newsbuff.util.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class NewsDataSource : PageKeyedDataSource<Int, NewsDTO>() {

    private var totalPages = 1

    val stateLiveData = MutableLiveData<State>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsDTO>
    ) {
        stateLiveData.postValue(State.LOADING)
        NewsRepository.getInstance().getTrendingNews(1)
            .enqueue(object : Callback<TrendingNewsResponse> {
                override fun onFailure(call: Call<TrendingNewsResponse>, t: Throwable) {
                    Timber.d("Failure : Trending News")
                    stateLiveData.postValue(State.ERROR)
                }

                override fun onResponse(
                    call: Call<TrendingNewsResponse>,
                    response: Response<TrendingNewsResponse>
                ) {
                    Timber.d("onResponse : Trending News")
                    stateLiveData.postValue(State.DONE)

                    if (response.body() != null) {
                        val trendingResponse = response.body() as TrendingNewsResponse
                        totalPages = trendingResponse.totalResults.div(20).plus(1)
                        Timber.d("Total Pages: $totalPages")

                        if (totalPages > 1) {
                            callback.onResult(response.body()!!.articles, null, 2)
                        }
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsDTO>) {
        stateLiveData.postValue(State.LOADING)
        NewsRepository.getInstance().getTrendingNews(params.key)
            .enqueue(object : Callback<TrendingNewsResponse> {
                override fun onFailure(call: Call<TrendingNewsResponse>, t: Throwable) {
                    Timber.d("Failure : Trending News")
                    stateLiveData.postValue(State.ERROR)
                }

                override fun onResponse(
                    call: Call<TrendingNewsResponse>,
                    response: Response<TrendingNewsResponse>
                ) {
                    Timber.d("onResponse : Trending News: ${params.key}")
                    stateLiveData.postValue(State.DONE)

                    if (totalPages >= params.key) {
                        callback.onResult(response.body()!!.articles, params.key + 1)
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsDTO>) {}
}