package com.mironov.newsapp.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mironov.newsapp.databinding.ItemArticleBinding
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article
import javax.inject.Inject

class ArticlesAdapter() : RecyclerView.Adapter<ArticleViewHolder>() {

    @Inject
    lateinit var glide: RequestManager

    lateinit var listener: ItemClickListener<ArticleViewHolder>

    private var _binding: ItemArticleBinding? = null

    private val binding get() = _binding!!

    var articles: ArrayList<Article> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var divide = false
        if (position == 0) {
            divide = true
        } else if (position > 1) {
            val datePrev = DateUtil.getDate(articles[position].publishedAt)
            val dateNext = DateUtil.getDate(articles[position - 1].publishedAt)
            if (datePrev != dateNext) {
                divide = true
            }
        }

        holder.bind(glide, articles[position], divide, listener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    interface ItemClickListener<I> : View.OnClickListener {
        fun onClickListener(item: ArticleViewHolder) {
        }

        override fun onClick(v: View?) {}
    }

}