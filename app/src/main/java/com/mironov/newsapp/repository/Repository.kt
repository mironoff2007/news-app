package com.mironov.newsapp.repository

import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.repository.retrofit.NewsApi
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor (protected var dataShared: DataShared, protected var retrofit: NewsApi){

    fun setNotFirstStartUp(){
        dataShared.setNotFirstStartUp()
    }

    fun isFirstStartUp(): Boolean {
        return dataShared.isFirstStartUp()
    }

    fun getNews(domain:String,dateFrom:String,dateTo:String,apiKey:String): Single<JsonResponse?> {
        return retrofit.getNews(
            domain=domain,
            dateFrom=dateFrom,
            dateTo=dateTo,
            apiKey=apiKey
        )
    }

}