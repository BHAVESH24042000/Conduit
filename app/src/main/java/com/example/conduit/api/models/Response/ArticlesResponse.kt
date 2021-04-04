package com.example.conduit.api.models.Response


import com.example.conduit.api.models.Entities.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesResponse(
        @Json(name = "articles")
    val articles: List<Article>,
        @Json(name = "articlesCount")
    val articlesCount: Int
)