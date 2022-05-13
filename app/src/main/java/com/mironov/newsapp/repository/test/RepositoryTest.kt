package com.mironov.newsapp.repository.test

import androidx.sqlite.db.SimpleSQLiteQuery
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.repository.RepositoryApi
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.repository.room.ArticleDatabase
import io.reactivex.Single
import javax.inject.Inject

class RepositoryTest @Inject constructor(
    private val retrofit: NewsApi,
    private val articleBD: ArticleDatabase
) : RepositoryApi {

    var isFirstStartup = false

    override fun setNotFirstStartUp() {
        isFirstStartup = false
    }

    override fun isFirstStartUp(): Boolean {
        return isFirstStartup
    }

    override fun getNews(
        pageSize: Int,
        domains: String,
        sources: String,
        language: String,
        dateFrom: String,
        dateTo: String,
        apiKey: String
    ): Single<JsonResponse?> {
        return retrofit.getNews(
            pageSize = pageSize,
            domains = domains,
            sources = sources,
            language = language,
            dateFrom = dateFrom,
            dateTo = dateTo,
            apiKey = apiKey
        )
    }

    override fun searchNews(
        query:String,
        pageSize:Int,
        domains: String,
        sources: String,
        language: String,
        apiKey: String
    ): Single<JsonResponse?> {
        return retrofit.searchNews(
            query = query,
            pageSize = pageSize,
            domains = domains,
            sources = sources,
            language = language,
            apiKey = apiKey
        )
    }

    override fun saveNewsToDb(articles: ArrayList<Article>) {
        articleBD.articleDao().insertAllArticles(articles)
    }

    override fun getNewsFromDbByDate(date: String): Single<List<Article>> {
        val queryDate = DateUtil.convertDate(date)
        val query = "SELECT * FROM Article WHERE date='$queryDate'"
        return articleBD.articleDao().readArticlesByDate(SimpleSQLiteQuery(query))
    }

}