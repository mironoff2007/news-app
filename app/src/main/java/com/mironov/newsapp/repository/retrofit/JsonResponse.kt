package com.mironov.newsapp.repository.retrofit
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mironov.newsapp.domain.entity.Article
import java.util.ArrayList

class JsonResponse {
    @SerializedName("status")
    var status: String? = null

    @Expose
    @SerializedName("message")
    var message: String? = null

    @Expose
    @SerializedName("totalResults")
    var totalResults: String? = null

    @SerializedName("articles")
    var articles: ArrayList<Article>? = null
}


