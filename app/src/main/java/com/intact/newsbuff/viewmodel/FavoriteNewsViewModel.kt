package com.intact.newsbuff.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.intact.newsbuff.data.Repository
import com.intact.newsbuff.pojo.NewsDTO

class FavoriteNewsViewModel(val repository: Repository, application: Application) : ViewModel() {

    var newsDTO : LiveData<List<NewsDTO>> = repository.getFavoriteNews()

}