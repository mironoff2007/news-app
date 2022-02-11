package com.mironov.newsapp.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.mironov.newsapp.R
import com.mironov.newsapp.databinding.FragmentNewsListBinding
import com.mironov.newsapp.databinding.FragmentSplashScreenBinding
import com.mironov.newsapp.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*

class SplashFragment : BaseFragment<FragmentSplashScreenBinding>() {

    companion object{
        const val TAG_SPLASH_SCREEN_FRAGMENT="TAG_SPLASH_SCREEN_FRAGMENT"
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashScreenBinding =
        FragmentSplashScreenBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sideAnimation = AnimationUtils.loadAnimation(requireContext(),
            R.anim.side_slide
        )
        binding.logoIcon.startAnimation(sideAnimation)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //requireContext().appComponent.inject(this)
    }

}