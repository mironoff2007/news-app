package com.mironov.newsapp.di.modules

import android.content.Context
import com.mironov.newsapp.repository.StringsProviderImpl
import dagger.Module
import dagger.Provides

@Module
object ResourcesModule {

    @Provides
    fun provideResources(context: Context): StringsProviderModule.StringsProvider = StringsProviderImpl(context.resources)
}