package com.mironov.newsapp.ui.recycler

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mironov.newsapp.R
import com.mironov.newsapp.databinding.ItemArticleBinding
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.repository.retrofit.Article
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(
    private val binding: ItemArticleBinding,
) : RecyclerView.ViewHolder(binding.root){
    @SuppressLint("SetTextI18n")
    fun bind(glide: RequestManager, article: Article, divide: Boolean){
        with(binding) {

            glide.asDrawable()
                .placeholder(R.drawable.ic_time)
                .error(R.drawable.ic_error)
                .load(article.urlToImage)
                .into(image)

            title.text = article.title
            description.text = Html.fromHtml(article.description)

            if (divide){
                dateRow.visibility=View.VISIBLE
                dateText.text=DateUtil.convertDate(article.publishedAt!!)
            }
            else{
                dateRow.visibility=View.GONE
            }


        }
    }
}
