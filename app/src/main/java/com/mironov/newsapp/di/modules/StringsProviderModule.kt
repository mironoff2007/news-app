package com.mironov.newsapp.di.modules

import androidx.annotation.StringRes

object StringsProviderModule{

    interface StringsProvider {
        fun getString(@StringRes stringId: Int): String
    }
}
