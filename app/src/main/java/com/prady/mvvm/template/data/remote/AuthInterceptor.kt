/*
 * *
 *  * Created by prady on 5/8/22, 9:06 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 5/4/22, 10:33 PM
 *
 */

package com.prady.mvvm.template.data.remote

import android.content.Context
import com.prady.mvvm.template.helpers.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add auth token to requests
 */
class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}