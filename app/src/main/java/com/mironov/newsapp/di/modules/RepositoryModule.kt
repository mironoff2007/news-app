package com.mironov.newsapp.di.modules

import com.mironov.newsapp.repository.Repository
import com.mironov.newsapp.repository.RepositoryApi
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(repo: Repository): RepositoryApi = repo
}