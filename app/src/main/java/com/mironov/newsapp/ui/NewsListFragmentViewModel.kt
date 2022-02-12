package com.mironov.newsapp.ui

import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.Repository
import javax.inject.Inject

class NewsListFragmentViewModel @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var repository: Repository


}