package com.mironov.newsapp.di.modules

import com.mironov.newsapp.domain.NewsResourceUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideNewsResourceUseCase(): NewsResourceUseCase {
        return NewsResourceUseCase()
    }
}