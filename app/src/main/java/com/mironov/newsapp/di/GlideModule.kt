package com.mironov.newsapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object GlideModule {

    @Singleton
    @Provides
    fun provideGlideRequestManager(context: Context): RequestManager {
        return Glide.with(context)
    }
}