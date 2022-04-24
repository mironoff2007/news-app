package com.mironov.newsapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.R
import com.mironov.newsapp.di.StringsProviderModule
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class NewsListFragmentViewModel @Inject constructor(private val repository: Repository, private val stringsProvider: StringsProviderModule.StringsProvider) : ViewModel() {

    var statusNewsByDate = MutableLiveData<Status>()

    var statusNewsSearch = MutableLiveData<Status>()

    private var disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getNews(daysBack: Int) {
        if (daysBack == 0) {
            //Always load today news from web
            getNewsFromWeb(daysBack)
        } else {
            val date = DateUtil.getPreviousDayDate(daysBack)

            disposables.add(repository.getNewsFromDbByDate(date).doOnSuccess { articles ->
                    if (articles.isEmpty()) {
                        getNewsFromWeb(daysBack)
                    } else {
                        statusNewsByDate.postValue(Status.DATA(articles as ArrayList<Article>?))
                    }
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({},
                    { throwable -> statusNewsByDate.postValue(Status.ERROR(throwable.toString())) })
            )
        }

    }

    @SuppressLint("CheckResult")
    fun getNewsFromWeb(daysBack: Int) {
        val date = DateUtil.getPreviousDayDate(daysBack)
        statusNewsByDate.postValue(Status.LOADING)
        disposables.add(
            repository.getNews(
                pageSize = NEWS_PAGE_SIZE,
                domains = NEWS_SOURCES_DOMAINS,
                language = NEWS_LANGUAGE,
                dateFrom = date,
                dateTo = date,
                apiKey = API_KEY
            )
                .subscribeOn(Schedulers.io())
                .doOnSuccess { response ->
                    if (response?.status == stringsProvider.getString(R.string.status_name_ok)) {
                        response.articles!!.forEach { article -> article.date = DateUtil.convertDate(article.publishedAt) }
                        statusNewsByDate.postValue(Status.DATA(response.articles))
                        response.articles.let { repository.saveNewsToDb(it!!) }
                    } else {
                        statusNewsByDate.postValue(Status.ERROR(response?.message ?: stringsProvider.getString(R.string.unknown_error)))
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { throwable -> statusNewsByDate.postValue(Status.ERROR(throwable.toString())) })
        )
    }

    @SuppressLint("CheckResult")
    fun searchNews(query: String) {
        statusNewsSearch.postValue(Status.LOADING)
        disposables.add(
            repository.searchNews(
                query,
                NEWS_PAGE_SIZE,
                NEWS_SOURCES_DOMAINS,
                NEWS_LANGUAGE,
                API_KEY
            )
                .subscribeOn(Schedulers.io())
                .doOnSuccess { response ->
                    if (response?.status == stringsProvider.getString(R.string.status_name_ok)) {
                        response.articles?.forEach { article ->
                            article.date = DateUtil.convertDate(article.publishedAt)
                        }
                        statusNewsSearch.postValue(Status.DATA(response.articles))
                    } else {
                        statusNewsSearch.postValue(Status.ERROR(response?.message ?: stringsProvider.getString(R.string.unknown_error)))
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { throwable -> statusNewsSearch.postValue(Status.ERROR(throwable.toString())) })
        )
    }

    fun dispose(){
        disposables.dispose()
    }

    companion object {
        const val NEWS_SOURCES_DOMAINS = "lenta.ru"
        const val NEWS_LANGUAGE = "ru"
        const val NEWS_PAGE_SIZE = 100
        const val API_KEY = "9fa809116bea45fa81e3d193cbaec5f0"
    }
}