package com.mironov.newsapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JsonError {
    @SerializedName("status")
    var status: String? = null
    @Expose
    @SerializedName("code")
    var code: String? = null
}


