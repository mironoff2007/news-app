package com.mironov.newsapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mironov.newsapp.di.DaggerTestAppComponent
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.NewsResources
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.di.tests_wrappers.RetrofitTestInjector
import com.mironov.newsapp.ui.NewsListFragmentViewModel
import com.mironov.newsapp.ui.NewsListFragmentViewModel.Companion.API_KEY
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RetrofitInstrumentedTest: RetrofitTestInjector() {

    private var appComponent: DaggerTestAppComponent

    init{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appComponent = appContext.applicationContext.appComponent as DaggerTestAppComponent
        appComponent.injectTest(this)
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {

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
                Log.d(TEST_TAG, "Success")
                response = it!!
            }
            .doOnError() { Log.d("My_tag", it.toString()) }
            .subscribe()

        Log.d(TEST_TAG, "Articles number - ${response?.articles?.size.toString()}")

        assertEquals(true, response?.status == "ok")
    }

    companion object{
        const val TEST_TAG = "Test_tag"
    }
}
