package com.mironov.newsapp.test

import com.mironov.newsapp.repository.Repository
import javax.inject.Inject

abstract class RetrofitTest {
    @Inject
    lateinit var repo: Repository
}