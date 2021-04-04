package com.example.conduit.api.models.Requests

import com.example.conduit.api.models.Entities.UserUpdateData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
//import com.example.conduit.api.models.Entities.UserUpdateData

@JsonClass(generateAdapter = true)
data class UserUpdateRequest(
        @Json(name = "user")
        val user: UserUpdateData
)