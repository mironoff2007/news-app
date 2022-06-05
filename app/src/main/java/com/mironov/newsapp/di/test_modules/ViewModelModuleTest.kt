package com.mironov.newsapp.di.test_modules

import androidx.lifecycle.ViewModel
import com.mironov.newsapp.di.TestScope
import com.mironov.newsapp.di.modules.viewmodel.ViewModelKey
import com.mironov.newsapp.ui.MainViewModel
import com.mironov.newsapp.ui.screens.newsdetails.NewsListFragmentViewModel
import com.mironov.newsapp.ui.screens.firststartup.StartUpInfoFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module()
interface ViewModelModuleTest {

    @TestScope
    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @TestScope
    @Binds
    @[IntoMap ViewModelKey(StartUpInfoFragmentViewModel::class)]
    fun provideStartUpInfoFragmentViewModel(startUpInfoFragmentViewModel: StartUpInfoFragmentViewModel): ViewModel

    @TestScope
    @Binds
    @[IntoMap ViewModelKey(NewsListFragmentViewModel::class)]
    fun provideNewsListFragmentViewModel(newsListFragmentViewModel: NewsListFragmentViewModel): ViewModel
}