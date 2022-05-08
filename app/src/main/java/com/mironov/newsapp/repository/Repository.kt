package com.mironov.newsapp.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.repository.room.ArticleDatabase
import io.reactivex.Single
import java.util.ArrayList
import javax.inject.Inject

class Repository @Inject constructor(
    protected val dataShared: DataShared,
    protected val retrofit: NewsApi,
    protected val articleBD: ArticleDatabase
) {

    fun setNotFirstStartUp() {
        dataShared.setNotFirstStartUp()
    }

    fun isFirstStartUp(): Boolean {
        return dataShared.isFirstStartUp()
    }

    fun getNews(
        pageSize:Int,
        domains: String,
        language: String,
        dateFrom: String,
        dateTo: String,
        apiKey: String
    ): Single<JsonResponse?> {
        return retrofit.getNews(
            pageSize = pageSize,
            domains = domains,
            language = language,
            dateFrom = dateFrom,
            dateTo = dateTo,
            apiKey = apiKey
        )
    }

    fun searchNews(
        query:String,
        pageSize:Int,
        domains: String,
        language: String,
        apiKey: String
    ): Single<JsonResponse?> {
        return retrofit.searchNews(
            query =query,
            pageSize =pageSize,
            domains = domains,
            language = language,
            apiKey = apiKey
        )
    }

    fun saveNewsToDb(articles:ArrayList<Article>){
        articleBD.articleDao().insertAllArticles(articles)
    }

    fun getNewsFromDbByDate(date:String): Single<List<Article>> {
        val queryDate=DateUtil.convertDate(date)
        val query="SELECT * FROM Article WHERE date='$queryDate'"
        return articleBD.articleDao().readArticlesByDate(SimpleSQLiteQuery(query))
    }

}