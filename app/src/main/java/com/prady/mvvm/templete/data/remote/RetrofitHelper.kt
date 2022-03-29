package com.prady.mvvm.templete.data.remote

import com.google.gson.GsonBuilder
import com.prady.srmgpc_user.helpers.Constraints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constraints.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}