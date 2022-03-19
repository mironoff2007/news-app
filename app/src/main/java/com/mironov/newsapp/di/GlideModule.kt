package com.mironov.newsapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.mironov.newsapp.repository.retrofit.UnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import java.io.InputStream


@Module
object GlideModule: AppGlideModule() {

    @Provides
    @AppScope
    fun provideGlideRequestManager(context: Context): RequestManager {
        return Glide.with(context)
    }

    override fun applyOptions(context: Context, builder: GlideBuilder)    {
        super.applyOptions(context, builder);
    }

    override fun registerComponents(context: Context, glide: Glide , registry: Registry) {
        val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient!!));
    }
}