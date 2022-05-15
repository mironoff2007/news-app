package com.mironov.newsapp.di.test_modules

import com.mironov.newsapp.di.TestScope
import com.mironov.newsapp.repository.DataShared
import com.mironov.newsapp.repository.Repository
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
    fun provideRepositoryTest(retrofit: NewsApi, articleDB: ArticleDatabase): RepositoryApi {
        return RepositoryTest(retrofit, articleDB)
    }

    @TestScope
    @Provides
    fun provideRepository(dataShared: DataShared, retrofit: NewsApi, articleDB: ArticleDatabase): Repository {
        return Repository(dataShared, retrofit, articleDB)
    }
}