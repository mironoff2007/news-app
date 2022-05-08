package com.mironov.newsapp.di.modules

import android.content.Context
import com.mironov.newsapp.di.AppScope
import com.mironov.newsapp.repository.room.ArticleDatabase
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