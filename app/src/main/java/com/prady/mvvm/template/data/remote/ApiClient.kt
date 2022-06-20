/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:32 PM
 *
 */

package com.prady.mvvm.template.data.remote

import com.google.gson.GsonBuilder
import com.prady.srmgpc_user.helpers.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    /**
     * It creates a Retrofit object with the base URL and the Gson converter factory
     *
     * @return An instance of Retrofit.
     */
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}