package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.di.viewmodel.MultiViewModelFactory
import com.mironov.newsapp.repository.room.ArticleDatabase
import com.mironov.newsapp.repository.DataShared
import com.mironov.newsapp.repository.Repository
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.DetailsFragment
import com.mironov.newsapp.ui.screens.GreetingFragment
import com.mironov.newsapp.ui.screens.GuideFragment
import com.mironov.newsapp.ui.screens.NewsListFragment
import dagger.*
import javax.inject.Scope

@Scope
annotation class AppScope

@Component(modules = [
    RepositoryModule::class,
    DataSharedModule::class,
    RetrofitModule::class,
    ViewModelModule::class,
    GlideModule::class,
    RoomModule::class,
    ResourcesModule::class
])

@AppScope
interface AppComponent  {
    fun inject(activity: MainActivity)
    fun inject(newsListFragment: NewsListFragment)
    fun inject(greetingFragment: GreetingFragment)
    fun inject(guideFragment: GuideFragment)
    fun inject(detailsFragment: DetailsFragment)

    val factory: MultiViewModelFactory

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}






