package com.intact.newsbuff.pojo

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "news")
data class NewsDTO(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url")
    var url: String,
    var urlToImage: String,
    var author: String,
    var title: String,
    var description: String,
    var publishedAt: String,
    var content: String,
    @Ignore
    var source: SourceDTO
) : Parcelable {
    constructor() : this("", "", "", "", "", "", "", SourceDTO())
}