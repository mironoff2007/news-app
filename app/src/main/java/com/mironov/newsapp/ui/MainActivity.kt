package com.mironov.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mironov.newsapp.R
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.ui.screens.firststartup.GreetingFragment
import com.mironov.newsapp.ui.screens.firststartup.GreetingFragment.Companion.TAG_GREETING_FRAGMENT
import com.mironov.newsapp.ui.screens.newslist.NewsListFragment
import com.mironov.newsapp.ui.screens.newslist.NewsListFragment.Companion.TAG_NEWS_LIST_FRAGMENT
import com.mironov.newsapp.ui.screens.SplashFragment
import com.mironov.newsapp.ui.screens.SplashFragment.Companion.TAG_SPLASH_SCREEN_FRAGMENT

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { applicationContext.appComponent.factory.create(MainViewModel::class.java) }

    private lateinit var observer : Observer<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {

        //Debug.waitForDebugger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    SplashFragment(), TAG_SPLASH_SCREEN_FRAGMENT
                )
                .commit()

            //background tasks while splash is showing

            viewModel.checkFirstRun()
        }
    }

    fun observe() {
        observer = Observer { isFirstRun: Boolean ->
            viewModel.isFirstRun.removeObserver(observer)
            showFragmentOnSplashEnd(isFirstRun)
        }
        viewModel.isFirstRun.observe(this, observer)
    }

    private fun showFragmentOnSplashEnd(isFirstRun:Boolean) {
        val fragment = if (isFirstRun) GreetingFragment() else NewsListFragment()
        val tag = if (isFirstRun) TAG_GREETING_FRAGMENT else TAG_NEWS_LIST_FRAGMENT

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .commit()
    }
}
