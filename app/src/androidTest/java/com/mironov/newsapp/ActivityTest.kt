package com.mironov.newsapp

import android.util.Log
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mironov.newsapp.RetrofitInstrumentedTest.Companion.TEST_TAG
import com.mironov.newsapp.di.DaggerTestAppComponent
import com.mironov.newsapp.di.TestAppComponent
import com.mironov.newsapp.di.tests_wrappers.ActivityTestInjector
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.repository.test.RepositoryTest
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.newsdetails.NewsListFragmentViewModel
import org.hamcrest.Matchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {

    @get:Rule
    var testName: TestName = TestName()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var viewModel: NewsListFragmentViewModel

    private class Injector: ActivityTestInjector()

    private val  injector = Injector()

    init {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val appComponent = appContext.applicationContext.appComponent as TestAppComponent
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
    fun before() {
        (injector.repository as RepositoryTest).setNotFirstStartUp()
    }

    @Test
    fun mainActivityTest() {
        Log.d(TEST_TAG, testName.methodName)

        (injector.repository as RepositoryTest).setNotFirstStartUp()

        val activityScenario = launch(MainActivity::class.java)

        activityScenario.onActivity {
            val status = Status.DATA(arrayListOf(article, article))
            viewModel.statusNewsByDate.postValue(status)
        }

        var recycler: RecyclerView? = null
        try {
            onView(instanceOf(RecyclerView::class.java))
                .check { view, noViewFoundException -> recycler = view as RecyclerView }
        }
        catch (e: java.lang.Exception) {
            Log.d(TEST_TAG, e.toString())
        }

        val count = recycler!!.adapter!!.itemCount

        Log.d(TEST_TAG, "count = $count")

        val hasItems = count > 0

        assert(hasItems)
    }

    @Test
    fun intoTest() {
        Log.d(TEST_TAG, testName.methodName)

        (injector.repository as RepositoryTest).reset()

        val activityScenario = launch(MainActivity::class.java)

        activityScenario.onActivity {
            viewModel.statusNewsByDate.postValue(
                Status.DATA(
                    arrayListOf(
                        article,
                        article
                    )
                )
            )
        }

        var infoFragment: View? = null
        try {
            onView(withId(R.id.info_fragment))
                .check { view, noViewFoundException -> infoFragment = view as View }
        } catch (e: java.lang.Exception) {
            Log.d(TEST_TAG, e.toString())
        }

        val fragmentExists = infoFragment != null

        assert(fragmentExists)
    }
}
