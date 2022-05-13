package com.mironov.newsapp.di

import android.content.Context
import com.mironov.newsapp.di.modules.*
import com.mironov.newsapp.di.modules.viewmodel.MultiViewModelFactory
import com.mironov.newsapp.di.test_modules.*
import com.mironov.newsapp.di.tests_wrappers.ActivityTestWrapper
import com.mironov.newsapp.di.tests_wrappers.NewsFragmentTest
import com.mironov.newsapp.di.tests_wrappers.RetrofitTest
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.DetailsFragment
import com.mironov.newsapp.ui.screens.GreetingFragment
import com.mironov.newsapp.ui.screens.GuideFragment
import com.mironov.newsapp.ui.screens.NewsListFragment
import dagger.*
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TestScope


@Component(modules = [
    DataSharedModule::class,
    RepositoryModuleTest::class,
    RetrofitModuleTest::class,
    ViewModelModuleTest::class,
    UseCaseModule::class,
    GlideModuleTest::class,
    RoomModuleTest::class,
    ResourcesModule::class
])

@TestScope
interface TestAppComponent:DaggerInterface {
    override fun inject(activity: MainActivity)
    override fun inject(newsListFragment: NewsListFragment)
    override fun inject(greetingFragment: GreetingFragment)
    override fun inject(guideFragment: GuideFragment)
    override fun inject(detailsFragment: DetailsFragment)
    fun injectTest(test: RetrofitTest)
    fun injectTest(test: NewsFragmentTest)
    fun injectTest(activityTest: ActivityTestWrapper)

    override val factory: MultiViewModelFactory

    @Component.Builder
    interface Builder {

        fun build(): TestAppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}






