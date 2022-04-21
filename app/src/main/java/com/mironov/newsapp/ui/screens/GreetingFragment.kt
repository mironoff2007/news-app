package com.mironov.newsapp.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.mironov.newsapp.R
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.databinding.FragmentInfoBinding
import com.mironov.newsapp.ui.StartUpInfoFragmentViewModel
import com.mironov.newsapp.ui.screens.GuideFragment.Companion.TAG_GUIDE_FRAGMENT
import kotlinx.android.synthetic.main.fragment_info.view.*

class GreetingFragment : BaseFragment<FragmentInfoBinding>() {

    private val viewModel by lazy { requireContext().appComponent.factory.create(StartUpInfoFragmentViewModel::class.java) }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInfoBinding=
        FragmentInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.visibility = View.INVISIBLE
        //accept
        binding.accept.setOnClickListener {
            viewModel.setNotFirstRun()
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    NewsListFragment(), NewsListFragment.TAG_NEWS_LIST_FRAGMENT
                )
                .commit()
        }
        //Go forward
        binding.buttonForward.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    GuideFragment(),TAG_GUIDE_FRAGMENT)
                .addToBackStack(null)
                .commit()
        }
        binding.header.text=requireContext().getString(R.string.greetings_text)
        binding.infoText.text=requireContext().getString(R.string.greetings_info)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    companion object{
        const val TAG_GREETING_FRAGMENT="TAG_GREETING_FRAGMENT"
    }
}