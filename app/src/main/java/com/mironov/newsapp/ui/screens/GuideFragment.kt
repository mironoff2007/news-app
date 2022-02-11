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
import com.mironov.newsapp.databinding.FragmentInfoBinding
import com.mironov.newsapp.ui.screens.NewsListFragment.Companion.TAG_NEWS_LIST_FRAGMENT
import kotlinx.android.synthetic.main.fragment_info.view.*

class GuideFragment : BaseFragment<FragmentInfoBinding>() {

    companion object {
        const val TAG_GUIDE_FRAGMENT = "TAG_GUIDE_FRAGMENT"
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInfoBinding =
        FragmentInfoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Go back
        (binding.buttonBack as ImageButton).setOnClickListener {
            parentFragmentManager
                .popBackStack()
        }
        //Go forward
        (binding.buttonForward as ImageButton).setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    NewsListFragment(), TAG_NEWS_LIST_FRAGMENT
                )
                .commit()
        }
        (binding.header as TextView).text=requireContext().getString(R.string.guide_header)
        (binding.infoText as TextView).text=requireContext().getString(R.string.guide_text)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //requireContext().appComponent.inject(this)
    }

}