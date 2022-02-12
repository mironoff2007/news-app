package com.mironov.newsapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.Status
import com.mironov.newsapp.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListFragmentViewModel @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var repository: Repository

    var status = MutableLiveData<Status>()

    @SuppressLint("CheckResult")
    fun getNews() {
        val todayDate=DateUtil.getTodayDate()
        status.postValue(Status.LOADING)
        repository.getNews(
            "bbc.com",
            "ru",
            todayDate,
            todayDate,
            "d6856a153473471887a271c3cd90b31e"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it!!.status == "ok") {
                    status.postValue(Status.DATA(it?.articles))
                } else {
                    Status.ERROR(it.message.toString())
                }
            }
            .doOnError {
                Status.ERROR(it.message.toString())
            }
            .subscribe({ }, { throwable -> Status.ERROR(throwable.toString()) })

    }
}