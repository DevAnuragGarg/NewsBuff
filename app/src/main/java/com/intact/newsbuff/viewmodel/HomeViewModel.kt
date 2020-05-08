package com.intact.newsbuff.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intact.newsbuff.api.NewsRepository
import com.intact.newsbuff.pojo.ErrorDTO
import com.intact.newsbuff.pojo.NewsDTO
import com.intact.newsbuff.pojo.TrendingNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _trendingNewsLiveData = MutableLiveData<ArrayList<NewsDTO>>()
    val trendingNewsLiveData: LiveData<ArrayList<NewsDTO>>
        get() = _trendingNewsLiveData

    private val _errorLiveData = MutableLiveData<ErrorDTO>()
    val errorLiveData: LiveData<ErrorDTO>
        get() = _errorLiveData

    fun getTrendingNews() {
        val call = NewsRepository.getInstance().getTrendingNews()
        call.enqueue(object : Callback<TrendingNewsResponse> {
            override fun onFailure(call: Call<TrendingNewsResponse>, t: Throwable) {
                _errorLiveData.value = ErrorDTO("Trending News", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<TrendingNewsResponse>,
                response: Response<TrendingNewsResponse>
            ) {
                _trendingNewsLiveData.value = response.body()?.articles
            }
        })
    }
}
