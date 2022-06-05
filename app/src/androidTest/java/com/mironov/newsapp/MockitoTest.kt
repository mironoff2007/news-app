package com.mironov.newsapp


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mironov.newsapp.di.DaggerTestAppComponent
import com.mironov.newsapp.di.tests_wrappers.ActivityTestInjector
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.repository.DataShared
import com.mironov.newsapp.repository.retrofit.JsonResponse
import com.mironov.newsapp.repository.retrofit.NewsApi
import com.mironov.newsapp.ui.screens.newsdetails.NewsListFragmentViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class MockitoTest {

    @get:Rule
    var testName: TestName = TestName()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var viewModel: NewsListFragmentViewModel

    private class Injector: ActivityTestInjector()

    private val  injector = Injector()

    init{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val appComponent = appContext.applicationContext.appComponent as DaggerTestAppComponent
        appComponent.injectTest(injector)

        viewModel = appComponent.factory.create(NewsListFragmentViewModel::class.java)
    }

    private val article = Article(
        title = "title",
        description = "Description",
        content = "Content",
        url = "url",
        urlToImage = "https://s0.rbk.ru/v6_top_pics/resized/590xH/media/img/0/23/756518538122230.jpg",
        publishedAt = "01-01-2022"
    )


    @Before
    @Throws(Exception::class)
    fun setUp() {}


    @Test
    fun mainActivityTest(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val dataShared = DataShared(context)

        val mockNewsApi = mock(NewsApi::class.java)

        Mockito.`when`(
            mockNewsApi.getNews(
                pageSize = anyInt(),
                domains = anyString(),
                sources = anyString(),
                language = anyString(),
                dateFrom = anyString(),
                dateTo = anyString(),
                apiKey = anyString()
            )
        ).thenReturn(Single.just(JsonResponse()))

    }

}
