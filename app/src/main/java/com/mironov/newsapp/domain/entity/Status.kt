package com.mironov.newsapp.domain.entity

import java.util.ArrayList

sealed class Status {
    object LOADING : Status()
    data class ERROR(var message:String) : Status()
    class DATA(val articles:ArrayList<Article>?): Status()
    object EMPTY : Status()
}
