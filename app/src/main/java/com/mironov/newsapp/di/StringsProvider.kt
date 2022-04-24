package com.mironov.newsapp.di

import androidx.annotation.StringRes

object StringsProviderModule{

    interface StringsProvider {
        fun getString(@StringRes stringId: Int): String
    }
}
