package com.intact.newsbuff.util.listeners

import com.intact.newsbuff.pojo.NewsDTO

interface OnNewsItemClickListener {
    fun onNewsItemClick(newsDTO: NewsDTO, showMenuItem: Boolean)

    fun onNewsFavoriteSelected(newsDTO: NewsDTO)
}