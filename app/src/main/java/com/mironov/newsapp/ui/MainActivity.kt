package com.mironov.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mironov.newsapp.R
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.ui.screens.NewsListFragment
import com.mironov.newsapp.ui.screens.NewsListFragment.Companion.TAG_NEWS_LIST_FRAGMENT
import com.mironov.newsapp.ui.screens.SplashFragment
import com.mironov.newsapp.ui.screens.SplashFragment.Companion.TAG_SPLASH_SCREEN_FRAGMENT

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        //Debug.waitForDebugger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    SplashFragment(),TAG_SPLASH_SCREEN_FRAGMENT)
                .commit()

            Handler().postDelayed({
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        NewsListFragment(),TAG_NEWS_LIST_FRAGMENT)
                    .commit()
            }, 2000)
        }
    }
}
