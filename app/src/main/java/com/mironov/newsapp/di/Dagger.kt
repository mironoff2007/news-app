package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.NewsListFragment
import dagger.*

@Component(modules = [AppModule::class,RetrofitModule::class])

interface AppComponent  {
    fun inject(activity: MainActivity)
    fun inject(newsListFragment: NewsListFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}

@Module
class AppModule() {

}






