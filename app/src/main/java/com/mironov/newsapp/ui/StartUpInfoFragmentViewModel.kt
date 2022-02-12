package com.mironov.newsapp.ui

import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.Repository
import javax.inject.Inject

class StartUpInfoFragmentViewModel @Inject constructor():ViewModel() {

    @Inject
    protected lateinit var repository: Repository

    fun checkFirstRun() {
        repository.setNotFirstStartUp()
    }
}