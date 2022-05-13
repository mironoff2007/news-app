package com.mironov.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.RepositoryApi
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repository: RepositoryApi) : ViewModel() {

    var isFirstRun = MutableLiveData<Boolean>()

    fun checkFirstRun() {
        isFirstRun.postValue(repository.isFirstStartUp())
    }
}