package com.mironov.newsapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mironov.newsapp.databinding.FragmentNewsListBinding

class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    companion object{
        const val TAG_NEWS_LIST_FRAGMENT="TAG_NEWS_LIST_FRAGMENT"
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsListBinding =
        FragmentNewsListBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //requireContext().appComponent.inject(this)
    }

}