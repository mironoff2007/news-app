package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.di.modules.*
import com.mironov.newsapp.di.modules.viewmodel.MultiViewModelFactory
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.newsdetails.DetailsFragment
import com.mironov.newsapp.ui.screens.firststartup.GreetingFragment
import com.mironov.newsapp.ui.screens.firststartup.GuideFragment
import com.mironov.newsapp.ui.screens.newslist.NewsListFragment
import dagger.*

@Component(modules = [
    RepositoryModule::class,
    RetrofitModule::class,
    ViewModelModule::class,
    UseCaseModule::class,
    GlideModule::class,
    RoomModule::class,
    ResourcesModule::class
])

@AppScope
interface AppComponent:DaggerInterface {
    override fun inject(activity: MainActivity)
    override fun inject(newsListFragment: NewsListFragment)
    override fun inject(greetingFragment: GreetingFragment)
    override fun inject(guideFragment: GuideFragment)
    override fun inject(detailsFragment: DetailsFragment)

    override val factory: MultiViewModelFactory

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}






