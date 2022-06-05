package com.mironov.newsapp.ui.screens.newsdetails

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.R
import com.mironov.newsapp.di.modules.StringsProviderModule
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.NewsResourceUseCase
import com.mironov.newsapp.domain.NewsResources
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.repository.RepositoryApi
import com.mironov.newsapp.repository.retrofit.JsonResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class NewsListFragmentViewModel @Inject constructor(
    val repository: RepositoryApi,
    private val stringsProvider: StringsProviderModule.StringsProvider,
    private val newsResourceUseCase: NewsResourceUseCase
) : ViewModel() {

    var statusNewsByDate = MutableLiveData<Status>()

    var statusNewsSearch = MutableLiveData<Status>()

    private var disposables = CompositeDisposable()

    private val source: NewsResources = NewsResources.LENTA

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

        val requestGetNews: (domain: String?, source: String?) -> Single<JsonResponse?> = { domain, source ->
            repository.getNews(
                pageSize = NEWS_PAGE_SIZE,
                domains = domain ?: "",
                sources =  source ?: "",
                language = NEWS_LANGUAGE,
                dateFrom = date,
                dateTo = date,
                apiKey = API_KEY
            )
        }
        disposables.add(
            newsResourceUseCase.execute(requestGetNews, source)
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

        val requestSearchNews: (domain: String?, source: String?) -> Single<JsonResponse?> = { domain, source->
            repository.searchNews(
                query = query,
                pageSize = NEWS_PAGE_SIZE,
                domains = domain ?: "",
                sources =  source ?: "",
                language = NEWS_LANGUAGE,
                apiKey = API_KEY
            )
        }

        disposables.add(
            newsResourceUseCase.execute(requestSearchNews, source)
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
                .doOnError { throwable -> statusNewsSearch.postValue(Status.ERROR(throwable.toString())) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { throwable -> statusNewsSearch.postValue(Status.ERROR(throwable.toString())) })

        )
    }

    fun dispose(){
        disposables.dispose()
    }

    companion object {
        const val NEWS_LANGUAGE = "ru"
        const val NEWS_PAGE_SIZE = 100
        const val API_KEY = "9fa809116bea45fa81e3d193cbaec5f0"
    }
}