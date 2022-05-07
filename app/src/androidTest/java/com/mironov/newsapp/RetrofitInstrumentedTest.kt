package com.mironov.newsapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mironov.newsapp.di.TestAppComponent
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.di.DaggerTestAppComponent
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.repository.Repository
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class RetrofitInstrumentedTest {
    private lateinit var appComponent: TestAppComponent

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        appComponent = DaggerTestAppComponent.builder()
            .context(appContext)
            .build()
        appComponent.inject(this)
    }

    @Inject
    lateinit var repo: Repository

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
