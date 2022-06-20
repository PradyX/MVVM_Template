/*
 * *
 *  * Created by prady on 5/8/22, 9:06 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 5/8/22, 9:06 PM
 *
 */

package com.prady.mvvm.template.helpers

import android.content.Context
import android.content.SharedPreferences
import com.prady.mvvm.template.R

/**
 * Session manager to save and fetch token from SharedPreferences
 */
class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}