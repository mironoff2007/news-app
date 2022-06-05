package com.mironov.newsapp.ui.screens.firststartup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.mironov.newsapp.R
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.databinding.FragmentInfoBinding
import com.mironov.newsapp.ui.screens.BaseFragment
import com.mironov.newsapp.ui.screens.newslist.NewsListFragment
import com.mironov.newsapp.ui.screens.newslist.NewsListFragment.Companion.TAG_NEWS_LIST_FRAGMENT

class GuideFragment : BaseFragment<FragmentInfoBinding>() {

    private val viewModel by lazy { requireContext().appComponent.factory.create(
        StartUpInfoFragmentViewModel::class.java) }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInfoBinding = FragmentInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Go back
        binding.buttonBack.setOnClickListener { parentFragmentManager.popBackStack() }
        //accept
        binding.accept.setOnClickListener {
            viewModel.setNotFirstRun()
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsListFragment(), TAG_NEWS_LIST_FRAGMENT)
                .commit()
        }
        binding.buttonForward.visibility = View.INVISIBLE
        binding.header.text = requireContext().getString(R.string.guide_header)
        binding.infoText.text = requireContext().getString(R.string.guide_text)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    companion object {
        const val TAG_GUIDE_FRAGMENT = "TAG_GUIDE_FRAGMENT"
    }
}