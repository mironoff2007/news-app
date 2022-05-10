package com.mironov.newsapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mironov.newsapp.di.*
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.NewsResources
import com.mironov.newsapp.test.RetrofitTest
import com.mironov.newsapp.ui.NewsListFragmentViewModel
import com.mironov.newsapp.ui.NewsListFragmentViewModel.Companion.API_KEY
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class RetrofitInstrumentedTest: RetrofitTest() {

    private lateinit var appComponent: DaggerTestAppComponent

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appComponent = appContext.applicationContext.appComponent as DaggerTestAppComponent
        appComponent.injectTest(this)
    }

    @SuppressLint("CheckResult")
    @Test
    fun apiNewsTest() {

        var response: JsonResponse? = null

        repo.getNews(
            pageSize = 10,
            domains = NewsResources.RBC.domain ?:"",
            sources =  NewsResources.RBC.source ?:"",
            language = NewsListFragmentViewModel.NEWS_LANGUAGE,
            apiKey = API_KEY,
            dateFrom = DateUtil.getTodayDate(),
            dateTo = DateUtil.getPreviousDayDate(1),
        )
            .doOnSuccess {
                Log.d("My_tag", "Success")
                response = it!!
            }
            .doOnError() { Log.d("My_tag", it.toString()) }
            .subscribe()
        Log.d("My_tag", response.toString())
        Log.d("My_tag", response?.articles?.size.toString())

        assertEquals(true, response?.status == "ok")
    }

}
