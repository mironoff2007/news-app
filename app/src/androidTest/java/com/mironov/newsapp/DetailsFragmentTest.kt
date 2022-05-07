package com.mironov.newsapp


import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario.Companion.launchInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mironov.newsapp.di.DaggerTestAppComponent
import com.mironov.newsapp.di.TestAppComponent
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.ui.screens.DetailsFragment
import com.mironov.newsapp.ui.screens.DetailsFragment.Companion.KEY_ARTICLE
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {
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

    @Test
    fun detailsFragmentTest() {
        val article = Article(
            title = "title",
            description = "Description",
            content = "Content",
            url = "url",
            urlToImage = "https://s0.rbk.ru/v6_top_pics/resized/590xH/media/img/0/23/756518538122230.jpg",
            publishedAt = "01-01-2022"
        )

        val fragmentArgs = bundleOf(KEY_ARTICLE to article)

        val scenario = launchInContainer(DetailsFragment::class.java, fragmentArgs, R.style.AppTheme, null)
        Thread.sleep(10000)
        scenario.recreate()
    }

}
