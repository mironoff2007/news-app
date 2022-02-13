package com.mironov.newsapp.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mironov.newsapp.databinding.ItemArticleBinding
import com.mironov.newsapp.domain.DateUtil
import com.mironov.newsapp.domain.entity.Article

class ArticlesAdapter(val glide:RequestManager) : RecyclerView.Adapter<ArticleViewHolder>() {

    private var _binding: ItemArticleBinding? = null

    private val binding get() = _binding!!

    var articles: ArrayList<Article> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var divide=false
        if (position == 0){
            divide=true
        }
        else if (position > 1){
            val datePrev = DateUtil.getDate(articles[position].publishedAt)
            val dateNext = DateUtil.getDate(articles[position - 1].publishedAt)
            if(datePrev!=dateNext){
                divide=true
            }
        }
        holder.bind(glide,articles[position],divide)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}