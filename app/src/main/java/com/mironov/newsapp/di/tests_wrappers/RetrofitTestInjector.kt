package com.mironov.newsapp.di.tests_wrappers

import com.mironov.newsapp.repository.Repository
import javax.inject.Inject

abstract class RetrofitTestInjector {
    @Inject
    protected lateinit var repo: Repository
}