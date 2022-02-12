package com.mironov.newsapp.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.databinding.FragmentNewsListBinding
import com.mironov.newsapp.ui.NewsListFragmentViewModel
import com.mironov.newsapp.ui.StartUpInfoFragmentViewModel
import com.mironov.newsapp.ui.Status
import com.mironov.newsapp.ui.recycler.ArticlesAdapter
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject

class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    companion object {
        const val TAG_NEWS_LIST_FRAGMENT = "TAG_NEWS_LIST_FRAGMENT"
    }

    @Inject
    lateinit var glide: RequestManager

    private lateinit var viewModel: NewsListFragmentViewModel

    private lateinit var adapter:ArticlesAdapter

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsListBinding =
        FragmentNewsListBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            requireContext().appComponent.factory.create(NewsListFragmentViewModel::class.java)

        adapter=ArticlesAdapter(glide)
        val layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter=adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )


        observe()

        viewModel.getNews()
    }

    fun observe() {
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.DATA -> {
                    binding.progressBar.visibility=View.GONE
                    adapter.articles= status.articles!!
                }
                is Status.LOADING -> {
                    binding.progressBar.visibility=View.VISIBLE
                }
                is Status.ERROR -> {
                    binding.progressBar.visibility=View.GONE
                    Toast.makeText(requireContext(),status.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
