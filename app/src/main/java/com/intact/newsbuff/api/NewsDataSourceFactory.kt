package com.intact.newsbuff.api

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.intact.newsbuff.pojo.NewsDTO

/**
 * data source factory that provide the data source
 */
class NewsDataSourceFactory : DataSource.Factory<Int, NewsDTO>() {

    private val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, NewsDTO> {
        val newsDataSource = NewsDataSource()
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}