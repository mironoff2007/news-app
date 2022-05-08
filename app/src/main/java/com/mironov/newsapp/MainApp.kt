package com.mironov.newsapp

import android.app.Application
import android.content.Context
import com.mironov.newsapp.di.*

class MainApp : Application() {

    @Synchronized
    fun isRunningTest(): Boolean {
        val isTest: Boolean = try {
            Class.forName("androidx.test.espresso.Espresso")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
        return isTest
    }

    lateinit var appComponent: DaggerInterface
        private set

    override fun onCreate() {
        super.onCreate()
        val isTest = isRunningTest()
        appComponent = if (isTest) {
            DaggerEspressoAppComponent.builder()
                .context(this)
                .build()
        } else {
            DaggerAppComponent.builder()
                .context(this)
                .build()
        }
    }
}

val Context.appComponent: DaggerInterface
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }