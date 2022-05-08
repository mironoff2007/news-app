package com.mironov.newsapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mironov.newsapp.di.*
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.test.RetrofitTest
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
            100,
            "lenta.ru",
            "ru",
            dateFrom = DateUtil.getTodayDate(),
            dateTo = DateUtil.getPreviousDayDate(2),
            "d6856a153473471887a271c3cd90b31e"
        )
            .doOnSuccess {
                Log.d("My_tag", "Success")
                response = it!!
            }
            .doOnError() { Log.d("My_tag", it.toString()) }
            .subscribe()
        Log.d("My_tag", response.toString())

        assertEquals(true, response?.status == "ok")
    }

}
