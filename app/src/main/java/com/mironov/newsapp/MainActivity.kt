package com.mironov.newsapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import com.mironov.newsapp.NewsListFragment.Companion.TAG_NEWS_LIST_FRAGMENT

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        //Debug.waitForDebugger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, NewsListFragment(),TAG_NEWS_LIST_FRAGMENT)
                .commit()
        }
    }
}
