package com.example.conduit.data

import com.example.conduit.api.ConduitClient
import com.example.conduit.api.models.Entities.LoginData
import com.example.conduit.api.models.Entities.SignupData

import com.example.conduit.api.models.Entities.User
import com.example.conduit.api.models.Entities.UserUpdateData
import com.example.conduit.api.models.Requests.LoginRequest
import com.example.conduit.api.models.Requests.UserUpdateRequest
import com.example.conduit.api.models.Response.SignupRequest

import com.example.conduit.api.models.Response.UserResponse
import com.example.conduit.api.services.ConduitAPI
import com.example.conduit.api.services.ConduitAuthAPI

object UserRepo {

    private val apiCall: ConduitAPI = ConduitClient.api
    private val authCall:ConduitAuthAPI = ConduitClient.Authapi

    suspend fun login(email: String, password:String ): UserResponse? {

        val response= apiCall.loginUser(LoginRequest(LoginData(email, password)))
        ConduitClient.authToken=response.body()?.user?.token
        return response.body()

    }

    suspend fun signup(username: String, email: String, password: String): UserResponse? {
        val response = apiCall.signupUser(SignupRequest(SignupData(email, password, username)))

        // TODO: Save it in SharedPreferences
        ConduitClient.authToken = response.body()?.user?.token

        return response.body()

    }
    suspend fun getUserProfile() = authCall.getCurrentUser().body()?.user
    suspend fun getCurrentUser(token: String):User? {
       ConduitClient.authToken=token
        return authCall.getCurrentUser().body()?.user
    }

    suspend fun updateUser(bio: String?, username: String?, image: String?, email: String?, password: String?): UserResponse? {
        val response = authCall.updateCurrentUser(UserUpdateRequest(UserUpdateData(bio, email, image, username, password)))

        return response.body()
    }


}