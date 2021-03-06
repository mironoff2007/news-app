package com.mironov.newsapp.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("content")
    var content: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("urlToImage")
    var urlToImage: String,

    @SerializedName("publishedAt")
    @PrimaryKey
    var publishedAt: String
) : Parcelable {

    var date: String? = null

}
