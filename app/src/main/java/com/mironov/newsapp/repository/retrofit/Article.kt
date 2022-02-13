package com.mironov.newsapp.repository.retrofit

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
    var publishedAt: String
) :Parcelable{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
