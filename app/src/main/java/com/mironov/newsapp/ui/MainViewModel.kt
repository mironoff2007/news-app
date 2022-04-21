package com.mironov.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    var isFirstRun = MutableLiveData<Boolean>()

    fun checkFirstRun() {
        isFirstRun.postValue(repository.isFirstStartUp())
    }
}