package com.example.conduit.data

import com.example.conduit.api.ConduitClient
import com.example.conduit.api.services.ConduitAPI
import com.example.conduit.api.services.ConduitAuthAPI

//import retrofit2.Retrofit


object ArticlesRepo {

    private val apicall: ConduitAPI = ConduitClient.api
    private val authcall:ConduitAuthAPI=ConduitClient.Authapi

    suspend fun getGlobalFeed()= apicall.getArticles()
    suspend fun getMyFeed()= authcall.getFeedArticles()

}