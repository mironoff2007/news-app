package com.mironov.newsapp.ui.screens

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.mironov.newsapp.R
import com.mironov.newsapp.appComponent
import com.mironov.newsapp.databinding.FragmentDetailsBinding
import com.mironov.newsapp.domain.entity.Article
import javax.inject.Inject

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    @Inject
    lateinit var glide: RequestManager

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButton.setImageResource(R.drawable.ic_arrow_left_black)

        val article = requireArguments().getParcelable<Article>(KEY_ARTICLE)

        binding.description.text = if(article?.description.isNullOrBlank()) article?.title else Html.fromHtml(article?.description)
        binding.date.text = article?.date

        glide.asDrawable()
            .placeholder(R.drawable.ic_time)
            .error(R.drawable.ic_error)
            .load(article?.urlToImage)
            .into(binding.image)

        binding.imageButton.setOnClickListener { goBack() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    private fun goBack() {
        parentFragmentManager.popBackStack()
    }

    companion object{
        const val TAG_DETAILS_FRAGMENT = "TAG_DETAILS_FRAGMENT"
        const val KEY_ARTICLE = "KEY_ARTICLE"
    }
}