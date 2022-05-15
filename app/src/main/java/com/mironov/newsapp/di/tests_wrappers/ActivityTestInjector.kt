package com.mironov.newsapp.di.tests_wrappers

import com.mironov.newsapp.repository.RepositoryApi
import javax.inject.Inject

abstract class ActivityTestInjector {
    @Inject
    lateinit var repository: RepositoryApi
}