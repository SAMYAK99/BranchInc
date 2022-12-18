package com.projects.trending.branchinternational.model


import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("auth_token")
    val authToken: String
)