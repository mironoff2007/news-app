package com.mironov.newsapp.di

import com.mironov.newsapp.repository.DataShared
import com.mironov.newsapp.repository.Repository
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.repository.room.ArticleDatabase
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(dataShared: DataShared, retrofit: NewsApi, articleDB: ArticleDatabase): Repository {
        return Repository(dataShared, retrofit, articleDB)
    }
}