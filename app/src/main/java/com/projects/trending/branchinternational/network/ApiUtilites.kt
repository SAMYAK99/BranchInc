package com.projects.trending.branchinternational.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilites  {


    // Create Retrofit Object
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://android-messaging.branch.co/")
        .build()

    // Create Service
    val service: ApiInterface = retrofit.create(ApiInterface::class.java)

}