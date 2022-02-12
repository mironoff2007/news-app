package com.mironov.newsapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mironov.newsapp.di.TestAppComponent
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.di.DaggerTestAppComponent
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import retrofit2.Call
import retrofit2.Response
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
    lateinit var retrofit: NewsApi

    @SuppressLint("CheckResult")
    @Test
    fun apiNewsTest() {

        var response: JsonResponse? = null

        val call: Single<JsonResponse?> =
            retrofit.getNews(
                "bbc.com",
                "2022-02-09",
                "2022-02-09",
                "d6856a153473471887a271c3cd90b31e"
            )

        call!!
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
