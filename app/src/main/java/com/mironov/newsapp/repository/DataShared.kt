package com.mironov.newsapp.repository

import android.content.Context
import android.content.SharedPreferences

class DataShared(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    fun setNotFirstStartUp() {
        editor.putBoolean(KEY_IS_FIRST_STARTUP, false).apply()
    }

    fun isFirstStartUp(): Boolean {
        return pref.getBoolean(KEY_IS_FIRST_STARTUP, true)
    }

    companion object {
        private const val KEY_IS_FIRST_STARTUP = "KEY_IS_FIRST_STARTUP"
        private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"
    }
}