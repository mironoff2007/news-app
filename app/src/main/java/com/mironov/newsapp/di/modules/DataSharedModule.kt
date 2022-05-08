package com.mironov.newsapp.di.modules

import android.content.Context
import com.mironov.newsapp.repository.DataShared
import dagger.Module
import dagger.Provides

@Module
object DataSharedModule {

    @Provides
    fun provideDataShared(context: Context): DataShared {
        return DataShared(context)
    }
}