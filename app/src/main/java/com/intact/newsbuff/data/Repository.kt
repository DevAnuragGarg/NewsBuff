package com.intact.newsbuff.data

import com.intact.newsbuff.db.NewsDAO
import com.intact.newsbuff.pojo.NewsDTO

class Repository(private val newsDAO: NewsDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    fun getFavoriteNews() = newsDAO.getAllFavoriteNews()

    suspend fun insert(news: NewsDTO) {
        newsDAO.insert(news)
    }
}