package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.di.modules.*
import com.mironov.newsapp.di.modules.viewmodel.MultiViewModelFactory
import com.mironov.newsapp.test.RetrofitTest
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.DetailsFragment
import com.mironov.newsapp.ui.screens.GreetingFragment
import com.mironov.newsapp.ui.screens.GuideFragment
import com.mironov.newsapp.ui.screens.NewsListFragment
import dagger.*

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
interface TestAppComponent:DaggerInterface {
    override fun inject(activity: MainActivity)
    override fun inject(newsListFragment: NewsListFragment)
    override fun inject(greetingFragment: GreetingFragment)
    override fun inject(guideFragment: GuideFragment)
    override fun inject(detailsFragment: DetailsFragment)
    fun injectTest(test: RetrofitTest)

    override val factory: MultiViewModelFactory

    @Component.Builder
    interface Builder {

        fun build(): TestAppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}






