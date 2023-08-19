package com.mironov.newsapp.repository

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class DataShared @Inject constructor(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    fun setNotFirstStartUp() {
        editor.putBoolean(KEY_IS_FIRST_STARTUP, false).apply()
    }

    fun isFirstStartUp(): Boolean {
        return pref.getBoolean(KEY_IS_FIRST_STARTUP, true)
    }

    fun clean() {
        pref.edit().clear().commit()
    }

    companion object {
        private const val KEY_IS_FIRST_STARTUP = "KEY_IS_FIRST_STARTUP"
        private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"
    }
}