package com.mironov.newsapp


import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario.Companion.launchInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mironov.newsapp.domain.entity.Article
import com.mironov.newsapp.ui.screens.newsdetails.DetailsFragment
import com.mironov.newsapp.ui.screens.newsdetails.DetailsFragment.Companion.KEY_ARTICLE
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

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
