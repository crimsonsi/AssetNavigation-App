package com.osl.survey

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager @SuppressLint("CommitPrefEdits")
constructor(context: Context, mode: Int) {

    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences("ut_manager",mode)
        editor = preferences.edit()
    }

    fun isFirstRun() = preferences.getBoolean(FIRST_TIME, true)

    fun setFirstRun() {
        editor.putBoolean(FIRST_TIME, false).commit()
        editor.commit()
    }

    fun saveToken (token:String) {
        editor.putString("token",token)
        editor.commit()
    }

    fun getToken () = preferences.getString("token","")


    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREFERENCE_CONFIGURATION_NAME = "configuration"
        private const val FIRST_TIME = "isFirstRun"
    }
}