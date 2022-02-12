package com.mironov.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mironov.newsapp.repository.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor() :ViewModel() {

    @Inject
    protected lateinit var repository: Repository

    var isFirstRun = MutableLiveData<Boolean>()

    fun checkFirstRun(){
        isFirstRun.postValue(repository.isFirstStartUp())
    }
}