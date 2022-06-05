package com.mironov.newsapp.screen

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mironov.newsapp.R
import com.kaspersky.kaspresso.screens.KScreen
import com.mironov.newsapp.ui.screens.newsdetails.DetailsFragment
import com.mironov.newsapp.ui.screens.firststartup.GreetingFragment
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object IntroScreen : KScreen<IntroScreen>() {

    override val layoutId: Int = R.layout.fragment_info
    override val viewClass: Class<*> = GreetingFragment::class.java

    val forwardButton = KButton { withId(R.id.buttonForward) }
}

object NewsListScreen : KScreen<NewsListScreen>() {

    override val layoutId: Int = R.layout.fragment_news_list
    override val viewClass: Class<*> = DetailsFragment::class.java

    val recycler: KRecyclerView = KRecyclerView({ withId(R.id.recyclerView) },
        itemTypeBuilder = { withId(R.id.item_article) })

    val noNewsHint = KTextView{ withId(R.id.no_news) }
    val dragHint = KTextView{ withId(R.id.drag_hint) }

}


