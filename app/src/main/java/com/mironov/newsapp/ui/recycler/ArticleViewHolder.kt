package com.mironov.newsapp.ui.recycler

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mironov.newsapp.R
import com.mironov.newsapp.databinding.ItemArticleBinding
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article

class ArticleViewHolder(
    private val binding: ItemArticleBinding,
) : RecyclerView.ViewHolder(binding.root){
    @SuppressLint("SetTextI18n")
    fun bind(
        article: Article,
        divide: Boolean,
        listener: ArticlesAdapter.ItemClickListener<ArticleViewHolder>
    ){
        with(binding) {


            Glide.with(preview).asDrawable()
                .placeholder(R.drawable.ic_time)
                .error(R.drawable.ic_error)
                .load(article.urlToImage)
                .into(preview)

            title.text = article.title

            time.text = DateUtil.convertTime(article.publishedAt)

            if (divide){
                dateRow.visibility = View.VISIBLE
                dateText.text = DateUtil.convertDate(article.publishedAt)
            }
            else{
                dateRow.visibility = View.GONE
            }
        }

        binding.root.setOnClickListener { listener.onClickListener(this) }
    }
}
