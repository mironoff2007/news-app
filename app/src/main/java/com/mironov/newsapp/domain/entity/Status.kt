package com.mironov.newsapp.domain.entity

import java.util.ArrayList

sealed class Status {

    var loading = false
    var noNews = false
    var dragHint = false

    object LOADING : Status(){
        init {
            loading = true
        }
    }

    data class ERROR(var message:String) : Status()

    class DATA(val articles:ArrayList<Article>?): Status()

    object EMPTY : Status() {
        init {
            noNews = true
            dragHint = true
        }
    }

    object NOTFOUND : Status() {
        init {
            noNews = true
        }
    }

}
