package com.mironov.newsapp.domain

import com.mironov.newsapp.repository.retrofit.Article
import java.util.ArrayList

sealed class Status {
    object LOADING : Status()
    data class ERROR(var message:String) : Status()
    class DATA(val articles:ArrayList<Article>?): Status()
    object EMPTY : Status()
}
