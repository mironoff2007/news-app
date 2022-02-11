package com.mironov.newsapp.repository.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    fun getNews(
        @Query("domains") domain: String,
        @Query("from") dateFrom: String,
        @Query("to") dateTo: String,
        @Query("apiKey") apiKey: String
    ): Call<JsonResponse?>
}
