package com.mironov.newsapp.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.databinding.FragmentNewsListBinding
import com.mironov.newsapp.ui.NewsListFragmentViewModel
import com.mironov.newsapp.ui.StartUpInfoFragmentViewModel

class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    companion object{
        const val TAG_NEWS_LIST_FRAGMENT="TAG_NEWS_LIST_FRAGMENT"
    }

    private lateinit var viewModel: NewsListFragmentViewModel

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
        viewModel = requireContext().appComponent.factory.create(NewsListFragmentViewModel::class.java)
    }

}