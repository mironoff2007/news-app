package com.mironov.newsapp.retrofit
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class JsonResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("articles")
    var articles: ArrayList<Article>?=null
}


