package com.coolgirl.madmeditations.Models

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val Url = "http://mskko2021.mad.hakta.pro/api/"
    private var retrofit: Retrofit? = null
    val interseptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    val client = OkHttpClient.Builder().addInterceptor(interseptor).build()

    fun start():Retrofit{
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}