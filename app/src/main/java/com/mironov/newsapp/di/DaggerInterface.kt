package com.mironov.newsapp.di

import com.mironov.newsapp.di.modules.viewmodel.MultiViewModelFactory
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.recycler.ArticlesAdapter
import com.mironov.newsapp.ui.screens.DetailsFragment
import com.mironov.newsapp.ui.screens.GreetingFragment
import com.mironov.newsapp.ui.screens.GuideFragment
import com.mironov.newsapp.ui.screens.NewsListFragment
import javax.inject.Scope

@Scope
annotation class AppScope

interface DaggerInterface {
    fun inject(activity: MainActivity)
    fun inject(newsListFragment: NewsListFragment)
    fun inject(greetingFragment: GreetingFragment)
    fun inject(guideFragment: GuideFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(adapter: ArticlesAdapter)

    val factory: MultiViewModelFactory
}