package com.mironov.newsapp.di

import com.mironov.newsapp.di.modules.viewmodel.MultiViewModelFactory
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.newsdetails.DetailsFragment
import com.mironov.newsapp.ui.screens.firststartup.GreetingFragment
import com.mironov.newsapp.ui.screens.firststartup.GuideFragment
import com.mironov.newsapp.ui.screens.newslist.NewsListFragment
import javax.inject.Scope

@Scope
annotation class AppScope

interface DaggerInterface {
    fun inject(activity: MainActivity)
    fun inject(newsListFragment: NewsListFragment)
    fun inject(greetingFragment: GreetingFragment)
    fun inject(guideFragment: GuideFragment)
    fun inject(detailsFragment: DetailsFragment)

    val factory: MultiViewModelFactory
}