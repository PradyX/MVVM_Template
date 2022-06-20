/*
 * *
 *  * Created by prady on 6/8/22, 11:14 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 6/8/22, 11:14 PM
 *
 */

package com.prady.mvvm.template.data.remote

import android.content.Context
import com.prady.srmgpc_user.helpers.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit instance class
 */
class ApiClientHeader {
    private lateinit var apiService: ApiService

    fun getApiService(context: Context): ApiService {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context)) // Add our Okhttp client
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }

    /**
     * Initialize OkhttpClient with our interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}