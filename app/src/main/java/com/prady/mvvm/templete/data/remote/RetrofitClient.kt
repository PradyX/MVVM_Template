package com.prady.mvvm.templete.data.remote

import com.google.gson.GsonBuilder
import com.prady.srmgpc_user.helpers.Constraints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var retrofit: Retrofit? = null
        get() {
            if (field == null) {
                field = Retrofit.Builder()
                    .baseUrl(Constraints.BaseUrl)
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    )
                    .build()
            }
            return field
        }
        private set
}