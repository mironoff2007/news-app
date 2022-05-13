package com.mironov.newsapp.repository

import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.repository.retrofit.JsonResponse
import io.reactivex.Single

interface RepositoryApi {

    fun setNotFirstStartUp()

    fun isFirstStartUp(): Boolean

    fun getNews(
        pageSize: Int,
        domains: String,
        sources: String,
        language: String,
        dateFrom: String,
        dateTo: String,
        apiKey: String
    ): Single<JsonResponse?>

    fun searchNews(
        query:String,
        pageSize:Int,
        domains: String,
        sources: String,
        language: String,
        apiKey: String
    ): Single<JsonResponse?>

    fun saveNewsToDb(articles: ArrayList<Article>)

    fun getNewsFromDbByDate(date: String): Single<List<Article>>
}