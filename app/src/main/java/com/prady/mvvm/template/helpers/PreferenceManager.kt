/*
 * *
 *  * Created by prady on 6/8/22, 11:14 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 6/8/22, 11:14 PM
 *
 */
package com.prady.mvvm.template.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferenceManager private constructor(context: Context) {

    var sharedPreferences: SharedPreferences
    var context: Context
    var editor: SharedPreferences.Editor

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: PreferenceManager
            private set

        @JvmStatic
        fun init(context: Context) {
            instance = PreferenceManager(context)
        }
    }

    init {
        sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        this.context = context
        editor = sharedPreferences.edit()
    }

    fun clear() {
        editor.clear()
        editor.apply()
        editor.commit()
    }

    var userid: String?
        get() = sharedPreferences.getString("userid", "")
        set(userid) {
            editor.putString("userid", userid)
            editor.commit()
        }
    var email: String?
        get() = sharedPreferences.getString("email", "")
        set(email) {
            editor.putString("email", email)
            editor.commit()
        }
    var token: String?
        get() = sharedPreferences.getString("Token", "")
        set(Token) {
            editor.putString("Token", Token)
            editor.commit()
        }
    var tempToken: String?
        get() = sharedPreferences.getString("TempToken", "")
        set(Token) {
            editor.putString("TempToken", Token)
            editor.commit()
        }
    var loggedIn: String?
        get() = sharedPreferences.getString("LoggedIn", "")
        set(Token) {
            editor.putString("LoggedIn", Token)
            editor.commit()
        }
}