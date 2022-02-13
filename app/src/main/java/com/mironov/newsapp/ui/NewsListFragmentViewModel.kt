package com.mironov.newsapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.repository.Repository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.from
import io.reactivex.parallel.ParallelFlowable.from
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class NewsListFragmentViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val NEWS_SOURCES_DOMAINS = "bbc.com"
        const val NEWS_LANGUAGE = "ru"
        const val NEWS_PAGE_SIZE = 100
        const val API_KEY = "d6856a153473471887a271c3cd90b31e"
    }

    @Inject
    protected lateinit var repository: Repository

    var status = MutableLiveData<Status>()

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
                        status.postValue(Status.DATA(articles as ArrayList<Article>?))
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { throwable -> status.postValue(Status.ERROR(throwable.toString())) })
        }

    }

    @SuppressLint("CheckResult")
    fun getNewsFromWeb(daysBack: Int) {
        val date = DateUtil.getPreviousDayDate(daysBack)
        status.postValue(Status.LOADING)
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
                    status.postValue(Status.DATA(response.articles))
                    repository.saveNewsToDb(response!!.articles!!)
                } else {
                    status.postValue(Status.ERROR(response.message.toString()))
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
            }, { throwable -> status.postValue(Status.ERROR(throwable.toString())) })
    }
}