package com.mironov.newsapp.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.mironov.newsapp.R
import com.mironov.newsapp.databinding.FragmentSplashScreenBinding
import com.mironov.newsapp.ui.MainActivity

class SplashFragment : BaseFragment<FragmentSplashScreenBinding>() {

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
        sideAnimation.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationEnd(animation: Animation?) {
                    (requireActivity() as MainActivity).observe()
                }

                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {}
            })
        binding.logoIcon.startAnimation(sideAnimation)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //requireContext().appComponent.inject(this)
    }

    companion object{
        const val TAG_SPLASH_SCREEN_FRAGMENT="TAG_SPLASH_SCREEN_FRAGMENT"
    }
}