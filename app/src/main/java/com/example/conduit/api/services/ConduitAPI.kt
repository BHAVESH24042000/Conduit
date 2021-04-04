package com.example.conduit.api.services

import com.example.conduit.api.models.Requests.LoginRequest
import com.example.conduit.api.models.Response.*
import retrofit2.Response
import retrofit2.http.*

interface ConduitAPI {

    //@GET("articles")
    //suspend fun getArticles(
        //@Query("author") author: String? = null,
       // @Query("favourited") favourited: String? = null,
       // @Query("tag") tag: String? = null
    //): Response<ArticlesResponse>

    @POST("users")
    suspend fun signupUser(
            @Body userCreds: SignupRequest
    ): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
           @Body userCreds: LoginRequest
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
            @Query("author") author: String? = null,
            @Query("favourited") favourited: String? = null,
            @Query("tag") tag: String? = null
    ): Response<ArticlesResponse>

    @GET("articles/{slug}")
    suspend fun getArticleBySlug(
            @Path("slug") slug: String
    ): Response<ArticleResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>

}