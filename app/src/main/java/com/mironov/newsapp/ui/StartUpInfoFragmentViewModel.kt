package com.mironov.newsapp.ui

import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.Repository
import javax.inject.Inject

class StartUpInfoFragmentViewModel @Inject constructor(var repository: Repository):ViewModel() {

    fun setNotFirstRun() {
        repository.setNotFirstStartUp()
    }
}