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
import com.mironov.newsapp.di.tests_wrappers.ActivityTestWrapper
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.repository.test.RepositoryTest
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.NewsListFragmentViewModel
import org.hamcrest.Matchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest: ActivityTestWrapper() {

    @get:Rule
    var testName: TestName = TestName()

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
    fun mainActivityTest(){
        Log.d(TEST_TAG, testName.methodName)

        val activityScenario = launch(MainActivity::class.java)

        activityScenario.onActivity { viewModel.statusNewsByDate.postValue(Status.DATA(arrayListOf(article, article))) }

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

        //For interaction with UI
        //Thread.sleep(10000)

        assert(count > 0)
    }

    @Test
    fun intoTest(){
        Log.d(TEST_TAG, testName.methodName)

        (repository as RepositoryTest).isFirstStartup = true

        val activityScenario = launch(MainActivity::class.java)

        activityScenario.onActivity { viewModel.statusNewsByDate.postValue(Status.DATA(arrayListOf(article, article))) }

        var infoFragment: View? = null
        try {
            onView(withId(R.id.info_fragment))
                .check { view, noViewFoundException -> infoFragment = view as View }
        }
        catch (e: java.lang.Exception) {
            Log.d(TEST_TAG, e.toString())
        }

        //Thread.sleep(10000)

        assert(infoFragment != null)
    }
}
