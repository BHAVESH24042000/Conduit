package com.example.conduit.api.models.Requests

import com.example.conduit.api.models.Entities.LoginData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
//import io.realworld.api.models.entities.LoginData
//import io.realworld.api.models.entities.SignupData

@JsonClass(generateAdapter = true)
data class LoginRequest(
        @Json(name = "user")
        val user: LoginData
)