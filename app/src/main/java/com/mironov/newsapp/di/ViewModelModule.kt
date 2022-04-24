package com.mironov.newsapp.di

import androidx.lifecycle.ViewModel
import com.mironov.newsapp.di.viewmodel.ViewModelKey
import com.mironov.newsapp.ui.MainViewModel
import com.mironov.newsapp.ui.NewsListFragmentViewModel
import com.mironov.newsapp.ui.StartUpInfoFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module()
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(StartUpInfoFragmentViewModel::class)]
    fun provideStartUpInfoFragmentViewModel(startUpInfoFragmentViewModel: StartUpInfoFragmentViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(NewsListFragmentViewModel::class)]
    fun provideNewsListFragmentViewModel(newsListFragmentViewModel: NewsListFragmentViewModel): ViewModel
}