package com.mironov.newsapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        status.postValue(Status.LOADING)
        repository.getNews(
            "bbc.com",
            "ru",
            "2022-02-09",
            "2022-02-09",
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