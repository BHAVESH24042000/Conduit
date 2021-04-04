package com.example.conduit.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduit.api.models.Entities.Article
import com.example.conduit.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel(){

    private val _feed= MutableLiveData<List<Article>>()
    private val _myfeed=MutableLiveData<List<Article>>()

    val feed:LiveData<List<Article>> =_feed

    val myfeed:LiveData<List<Article>> =_myfeed

    fun fetchGlobalFeed()=viewModelScope.launch {
        ArticlesRepo.getGlobalFeed().body()?.let {
            _feed.postValue(it.articles)
        }
    }


    fun fetchMyFeed() = viewModelScope.launch {
        ArticlesRepo.getMyFeed().body()?.let {
            _myfeed.postValue(it.articles)
        }
    }
}