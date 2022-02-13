package com.mironov.newsapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class NewsListFragmentViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val NEWS_SOURCES_DOMAINS = "bbc.com"
        const val NEWS_LANGUAGE = "ru"
        const val NEWS_PAGE_SIZE = 100
        const val API_KEY = "PUT_YOUR_API_KEY_HERE"
    }

    @Inject
    protected lateinit var repository: Repository

    var statusNewsByDate = MutableLiveData<Status>()

    var statusNewsSearch = MutableLiveData<Status>()

    @SuppressLint("CheckResult")
    fun getNews(daysBack: Int) {
        if (daysBack == 0) {
            //Always load today news from web
            getNewsFromWeb(daysBack)
        } else {
            val date = DateUtil.getPreviousDayDate(daysBack)

            repository.getNewsFromDbByDate(date)
                .doOnSuccess { articles ->
                    if (articles.isEmpty()) {
                        getNewsFromWeb(daysBack)
                    } else {
                        statusNewsByDate.postValue(Status.DATA(articles as ArrayList<Article>?))
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { throwable -> statusNewsByDate.postValue(Status.ERROR(throwable.toString())) })
        }

    }

    @SuppressLint("CheckResult")
    fun getNewsFromWeb(daysBack: Int) {
        val date = DateUtil.getPreviousDayDate(daysBack)
        statusNewsByDate.postValue(Status.LOADING)
        repository.getNews(
            NEWS_PAGE_SIZE,
            NEWS_SOURCES_DOMAINS,
            NEWS_LANGUAGE,
            dateFrom = date,
            dateTo = date,
            API_KEY
        )
            .doOnSuccess { response ->
                if (response!!.status == "ok") {
                    response.articles!!.forEach{article ->  article.date=DateUtil.convertDate(article.publishedAt)}
                    statusNewsByDate.postValue(Status.DATA(response.articles))
                    repository.saveNewsToDb(response!!.articles!!)
                } else {
                    statusNewsByDate.postValue(Status.ERROR(response.message.toString()))
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
            }, { throwable -> statusNewsByDate.postValue(Status.ERROR(throwable.toString())) })
    }

    @SuppressLint("CheckResult")
    fun searchNews(query:String) {
        statusNewsSearch.postValue(Status.LOADING)
        repository.searchNews(
            query,
            NEWS_PAGE_SIZE,
            NEWS_SOURCES_DOMAINS,
            NEWS_LANGUAGE,
            API_KEY
        )
            .doOnSuccess { response ->
                if (response!!.status == "ok") {
                    response.articles!!.forEach{article ->  article.date=DateUtil.convertDate(article.publishedAt)}
                    statusNewsSearch.postValue(Status.DATA(response.articles))
                } else {
                    statusNewsSearch.postValue(Status.ERROR(response.message.toString()))
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
            }, { throwable -> statusNewsSearch.postValue(Status.ERROR(throwable.toString())) })
    }
}