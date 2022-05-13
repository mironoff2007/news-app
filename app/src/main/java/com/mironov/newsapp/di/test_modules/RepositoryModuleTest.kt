package com.mironov.newsapp.di.test_modules

import com.mironov.newsapp.di.TestScope
import com.mironov.newsapp.repository.RepositoryApi
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.repository.room.ArticleDatabase
import com.mironov.newsapp.repository.test.RepositoryTest
import dagger.Module
import dagger.Provides

@Module
object RepositoryModuleTest {

    @TestScope
    @Provides
    fun provideRepository(retrofit: NewsApi, articleDB: ArticleDatabase): RepositoryApi {
        return RepositoryTest(retrofit, articleDB)
    }
}