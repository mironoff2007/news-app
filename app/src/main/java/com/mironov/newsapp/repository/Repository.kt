package com.mironov.newsapp.repository
import com.mironov.newsapp.repository.DataShared
import com.mironov.newsapp.repository.retrofit.NewsApi
import javax.inject.Inject

class Repository @Inject constructor (protected var dataShared: DataShared, protected var retrofit: NewsApi){

}