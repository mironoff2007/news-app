package com.mironov.newsapp

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario.Companion.launchInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mironov.newsapp.RetrofitInstrumentedTest.Companion.TEST_TAG
import com.mironov.newsapp.di.DaggerTestAppComponent
import com.mironov.newsapp.di.tests_wrappers.NewsFragmentTestInjector
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.ui.screens.newsdetails.NewsListFragmentViewModel
import com.mironov.newsapp.ui.screens.newslist.NewsListFragment
import org.hamcrest.Matchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsFragmentTest : NewsFragmentTestInjector() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var viewModel: NewsListFragmentViewModel

    init{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val appComponent = appContext.applicationContext.appComponent as DaggerTestAppComponent
        appComponent.injectTest(this)

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
    fun newsFragmentTest(){

        val scenario = launchInContainer(NewsListFragment::class.java, bundleOf(), R.style.AppTheme, null)

        scenario.onFragment { viewModel.statusNewsByDate.postValue(Status.DATA(arrayListOf(article, article))) }

        var recycler: RecyclerView? = null

        try {
            onView(instanceOf(RecyclerView::class.java))
                .check { view, noViewFoundException -> recycler = view as RecyclerView }
        }
        catch (e: java.lang.Exception) {
            Log.d(TEST_TAG, e.toString())
        }

        val count = recycler!!.adapter!!.itemCount

        Thread.sleep(1000)

        assert(count == 2)
    }

}
