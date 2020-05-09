package com.intact.newsbuff.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.intact.newsbuff.api.NewsDataSourceFactory
import com.intact.newsbuff.pojo.NewsDTO

/**
 * PagedList is content-immutable. This means that, although new content can be loaded
 * into an instance of PagedList, the loaded items themselves cannot change once loaded.
 * As such, if content in a PagedList updates, the PagedListAdapter object receives a
 * completely new PagedList that contains the updated information.
 *
 * Placeholders: In cases where you want your UI to display a list before your app has
 * finished fetching data, you can show placeholder list items to your users. The PagedList
 * handles this case by presenting the list item data as null until the data is loaded.
 * By default, the Paging Library enables this placeholder behavior.
 */
class HomeViewModel : ViewModel() {

    private val pageSize = 20
    var newsList: LiveData<PagedList<NewsDTO>>
    private val newsDataSourceFactory = NewsDataSourceFactory()

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .setEnablePlaceholders(true)
            .build()

        // creating live pages list builder
        newsList = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }
}
