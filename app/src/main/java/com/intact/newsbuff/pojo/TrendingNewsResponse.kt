package com.intact.newsbuff.pojo

import java.util.*

data class TrendingNewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<NewsDTO>
)