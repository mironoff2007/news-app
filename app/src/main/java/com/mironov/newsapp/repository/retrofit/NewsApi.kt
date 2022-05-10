package com.mironov.newsapp.repository.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    fun getNews(
        @Query("pageSize") pageSize: Int,
        @Query("domains") domains: String? = null,
        @Query("sources") sources: String? = null,
        @Query("language") language: String,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
        @Query("apiKey") apiKey: String
    ): Single<JsonResponse?>

    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("domains") domains: String? = null,
        @Query("sources") sources: String? = null,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): Single<JsonResponse?>
}
