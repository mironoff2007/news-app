package com.mironov.newsapp.repository

import android.content.res.Resources
import com.mironov.newsapp.di.modules.StringsProviderModule
import javax.inject.Inject

class StringsProviderImpl @Inject constructor(private val resources: Resources) : StringsProviderModule.StringsProvider {

    override fun getString(stringId: Int): String {
        return resources.getString(stringId)
    }
}