package com.mironov.newsapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("content")
    var content: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("urlToImage")
    var urlToImage: String? = null

    @SerializedName("publishedAt")
    var publishedAt: String? = null

}
