package com.intact.newsbuff.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsDTO(
    val source: SourceDTO,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
): Parcelable