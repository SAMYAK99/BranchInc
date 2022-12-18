package com.projects.trending.branchinternational.network

import com.projects.trending.branchinternational.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @POST("api/login")
    suspend fun loginUser(@Body userRequest: UserRequest) : Response<Auth>

    @GET("api/messages")
    suspend fun getMessages(@Header("X-Branch-Auth-Token") authorization  : String)
                  :Response<ArrayList<MessagesItem>>

    @POST("api/messages")
    suspend fun postMessage(
        @Header("X-Branch-Auth-Token") authorization  : String,
        @Body messageRequest: MessageRequest ) : Response<MessagesItem>

}