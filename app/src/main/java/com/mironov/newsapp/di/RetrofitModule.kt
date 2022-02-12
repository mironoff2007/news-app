package com.mironov.newsapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.mironov.newsapp.repository.retrofit.NewsApi
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
object RetrofitModule {

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls()
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    fun provideNewsApi(retrofit: Retrofit.Builder): NewsApi {
        return retrofit
            .build()
            .create(NewsApi::class.java)
    }
}




















