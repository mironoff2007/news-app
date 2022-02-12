package com.mironov.newsapp.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mironov.newsapp.databinding.ItemArticleBinding
import com.mironov.newsapp.repository.retrofit.Article
import javax.inject.Inject

class ArticlesAdapter() : RecyclerView.Adapter<ArticleViewHolder>() {

    @Inject
    lateinit var glide: RequestManager

    private var _binding: ItemArticleBinding? = null

    private val binding get() = _binding!!

    var articles: ArrayList<Article> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(glide,articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}