package com.mironov.newsapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mironov.newsapp.R
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.databinding.FragmentNewsListBinding
import com.mironov.newsapp.domain.entity.Status
import com.mironov.newsapp.ui.NewsListFragmentViewModel
import com.mironov.newsapp.ui.recycler.ArticleViewHolder
import com.mironov.newsapp.ui.recycler.ArticlesAdapter
import com.mironov.newsapp.ui.screens.DetailsFragment.Companion.KEY_ARTICLE
import com.mironov.newsapp.ui.screens.DetailsFragment.Companion.TAG_DETAILS_FRAGMENT
import javax.inject.Inject

class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    @Inject
    lateinit var glide: RequestManager

    private val viewModel by lazy { requireContext().appComponent.factory.create(NewsListFragmentViewModel::class.java) }

    private var adapter: ArticlesAdapter? = null

    private var daysBack = 0
    private var daysBackLast = 0

    private var lockScrollUpdate = false

    private val listener = object : ArticlesAdapter.ItemClickListener<ArticleViewHolder> {
        override fun onClickListener(item: ArticleViewHolder) {

            val fragment = DetailsFragment()
            val argumentsDetails = Bundle()
            argumentsDetails.putParcelable(KEY_ARTICLE, adapter!!.articles[item.layoutPosition])

            fragment.arguments = argumentsDetails

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, TAG_DETAILS_FRAGMENT)
                .addToBackStack(TAG_NEWS_LIST_FRAGMENT)
                .commit()
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsListBinding =
        FragmentNewsListBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupScrollEndListener()

        observeNewsByDate()
        observeNewsSearch()

        if(adapter != null){
            binding.progressBar.visibility = View.GONE
            adapter!!.notifyDataSetChanged()
        }
        else{
            adapter = ArticlesAdapter(glide)
            adapter!!.listener = listener

            viewModel.getNews(daysBack)
        }

        val layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

        binding.searchButton.setOnClickListener { search() }
    }

    private fun search() {
        daysBack = 0
        adapter!!.articles.clear()
        if(binding.searchField.text.isNotBlank()){
            lockScrollUpdate = true
            viewModel.searchNews(binding.searchField.text.toString())
        }
        else{
            viewModel.getNews(0)
        }
    }

    private fun setupScrollEndListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !lockScrollUpdate) {
                    lockScrollUpdate = true
                    daysBack++
                    viewModel.getNews(daysBack)

                }
                daysBackLast = daysBack
            }
        })
    }

    private fun observeNewsByDate() {
        viewModel.statusNewsByDate.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.DATA -> {
                    lockScrollUpdate = false
                    binding.progressBar.visibility = View.GONE
                    adapter!!.articles.addAll(status.articles!!)
                    adapter!!.notifyDataSetChanged()
                }
                is Status.LOADING -> {
                    lockScrollUpdate = true
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Status.ERROR -> {
                    lockScrollUpdate = false
                    daysBack = daysBackLast
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_LONG).show()
                }
                is Status.EMPTY -> {
                    lockScrollUpdate = false
                    daysBack = daysBackLast
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), getString(R.string.no_news), Toast.LENGTH_LONG).show()
                }
                else -> {
                }
            }
        }
    }

    private fun observeNewsSearch() {
        viewModel.statusNewsSearch.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.DATA -> {
                    binding.progressBar.visibility = View.GONE
                    daysBack = 0
                    adapter!!.articles.clear()
                    adapter!!.articles?.addAll(status.articles!!)
                    adapter!!.notifyDataSetChanged()
                }
                is Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_LONG).show()
                }
                is Status.EMPTY -> {
                    lockScrollUpdate = false
                    daysBack = daysBackLast
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), getString(R.string.no_news), Toast.LENGTH_LONG).show()
                }
                else -> {
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    companion object {
        const val TAG_NEWS_LIST_FRAGMENT = "TAG_NEWS_LIST_FRAGMENT"
    }
}
