package com.mironov.newsapp


import android.Manifest
import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.mironov.newsapp.RetrofitInstrumentedTest.Companion.TEST_TAG
import com.mironov.newsapp.di.DaggerTestAppComponent
import com.mironov.newsapp.di.tests_wrappers.ActivityTestInjector
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.repository.test.RepositoryTest
import com.mironov.newsapp.screen.IntroScreen
import com.mironov.newsapp.screen.NewsListScreen
import com.mironov.newsapp.ui.MainActivity
import com.mironov.newsapp.ui.screens.newsdetails.NewsListFragmentViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTestKaspresso : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    var testName: TestName = TestName()

   /* @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()*/

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private class Injector: ActivityTestInjector()

    private val  injector = Injector()

    private var viewModel: NewsListFragmentViewModel

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
    fun before(){
        activityRule.scenario.recreate()
    }

    @Test
    fun mainActivityTestNoNewsByDate() {
        Log.d(TEST_TAG, testName.methodName)

        before {
            testLogger.i("Before section")
        }.after {
            testLogger.i("After section")
        }.run {
            step("Open Simple Screen") {
                NewsListScreen {
                    while(viewModel.statusNewsByDate.value != Status.EMPTY){
                        Thread.sleep(100)
                        viewModel.statusNewsByDate.postValue(Status.EMPTY)
                    }
                    noNewsHint {
                        isVisible()
                    }
                    dragHint {
                        isVisible()
                    }
                }
            }
        }
    }

    @Test
    fun mainActivityTestNoNewsBySearch() {
        Log.d(TEST_TAG, testName.methodName)

        before {
            testLogger.i("Before section")
        }.after {
            testLogger.i("After section")
        }.run {
            step("Open Simple Screen") {
                NewsListScreen {
                    while(viewModel.statusNewsSearch.value != Status.EMPTY){
                        Thread.sleep(100)
                        viewModel.statusNewsSearch.postValue(Status.EMPTY)
                    }
                    noNewsHint {
                        isVisible()
                    }
                    dragHint {
                        isGone()
                    }
                }
            }
        }
    }

    @Test
    fun mainActivityTest2(){
        Log.d(TEST_TAG, testName.methodName)

        (injector.repository as RepositoryTest).isFirstStartup = true
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        before {
            testLogger.i("Before section")
        }.after {
            testLogger.i("After section")
            (injector.repository as RepositoryTest).isFirstStartup = false
        }.run{
            step("Open Simple Screen") {

                testLogger.i("Main section")

                IntroScreen {
                    forwardButton {
                        isVisible()
                        click()
                    }
                    device.screenshots.take("intoTest_Screenshot")
                }
            }
        }
    }

    @Test
    fun mainActivityTest3(){
        Log.d(TEST_TAG, testName.methodName)

        before {
            testLogger.i("Before section")
             }.after {
            testLogger.i("After section")
        }.run {

            step("Open Simple Screen") {
                NewsListScreen {
                    viewModel.statusNewsByDate.postValue(Status.DATA(arrayListOf(article, article)))
                    recycler {
                        hasSize(2)
                    }
                }
            }
        }
    }

}
