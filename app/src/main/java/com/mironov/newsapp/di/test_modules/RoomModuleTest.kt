package com.mironov.newsapp.di.test_modules

import android.content.Context
import com.mironov.newsapp.di.AppScope
import com.mironov.newsapp.di.TestScope
import com.mironov.newsapp.repository.room.ArticleDatabase
import dagger.Module
import dagger.Provides

@Module
object RoomModuleTest {

    @Provides
    @TestScope
    fun provideArticleDatabase(context:Context): ArticleDatabase {
        return ArticleDatabase.getDatabase(context)
    }
}