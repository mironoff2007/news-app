package com.mironov.newsapp.repository.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    fun getNews(
        @Query("domains") domain: String,
        @Query("from") dateFrom: String,
        @Query("to") dateTo: String,
        @Query("apiKey") apiKey: String
    ): Single<JsonResponse?>
}
