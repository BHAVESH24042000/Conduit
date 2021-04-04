package com.example.conduit.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduit.api.ConduitClient
import com.example.conduit.api.models.Entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel:ViewModel() {
    val api = ConduitClient.api

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun fetchArticle(slug: String) = viewModelScope.launch {
        val response = api.getArticleBySlug(slug)

        response.body()?.article.let {
            _article.postValue(it)
        }

    }

}