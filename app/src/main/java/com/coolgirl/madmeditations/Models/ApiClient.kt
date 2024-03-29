package com.coolgirl.madmeditations.Models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    val baseUrl = "mskko2021.mad.hakta.pro/api/"
    private var retrofit: Retrofit? = null

    fun start():Retrofit{
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}