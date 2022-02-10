package com.mironov.newsapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("everything")
    fun getNews(
        @Query("apiKey") apiKey: String
    ): Call<JsonResponse?>
}
