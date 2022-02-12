package com.mironov.newsapp.repository

import com.mironov.newsapp.repository.retrofit.NewsApi
import javax.inject.Inject

class Repository @Inject constructor (protected var dataShared: DataShared, protected var retrofit: NewsApi){

    fun setNotFirstStartUp(){
        dataShared.setNotFirstStartUp()
    }

    fun isFirstStartUp(): Boolean {
        return dataShared.isFirstStartUp()
    }

}