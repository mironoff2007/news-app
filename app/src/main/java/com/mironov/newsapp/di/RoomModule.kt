package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.repository.ArticleDatabase
import dagger.Module
import dagger.Provides

@Module
object RoomModule {

    @Provides
    @AppScope
    fun provideArticleDatabase(context:Context): ArticleDatabase {
        return ArticleDatabase.getDatabase(context)
    }
}