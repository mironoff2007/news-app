package com.mironov.newsapp.di.tests_wrappers

import com.mironov.newsapp.repository.RepositoryApi
import javax.inject.Inject

abstract class ActivityTestWrapper {
    @Inject
    protected lateinit var repository: RepositoryApi
}