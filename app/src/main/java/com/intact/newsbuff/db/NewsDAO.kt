package com.intact.newsbuff.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intact.newsbuff.pojo.NewsDTO

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: NewsDTO)

    @Query("SELECT * FROM news")
    fun getAllFavoriteNews(): LiveData<List<NewsDTO>>
}