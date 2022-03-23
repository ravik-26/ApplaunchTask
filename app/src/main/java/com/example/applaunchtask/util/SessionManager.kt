package com.example.applaunchtask.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.applaunchtask.util.Constants.IS_USER_LOGGED_IN
import com.example.applaunchtask.util.Constants.PREF_NAME

class SessionManager(context: Context) {
    private var sharedPreferences: SharedPreferences = context
        .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isUserLoggedIn(): Boolean = sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false)

    fun createSession() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_USER_LOGGED_IN, true)

        // commit changes
        editor.apply()
    }

    fun clearSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}