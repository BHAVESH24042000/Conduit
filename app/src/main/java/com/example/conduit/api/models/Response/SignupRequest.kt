package com.example.conduit.api.models.Response


import com.example.conduit.api.models.Entities.SignupData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignupRequest(
    @Json(name = "user")
    val user: SignupData
)