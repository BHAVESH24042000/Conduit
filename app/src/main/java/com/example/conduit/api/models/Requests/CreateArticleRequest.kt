package com.example.conduit.api.models.Requests

import com.example.conduit.api.models.Entities.ArticleData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
//import io.realworld.api.models.entities.ArticleData

@JsonClass(generateAdapter = true)
data class CreateArticleRequest(
        @Json(name = "article")
        val article: ArticleData
)