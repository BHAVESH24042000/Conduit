package com.example.conduit.ui.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduit.api.models.Entities.User
import com.example.conduit.data.UserRepo
import kotlinx.coroutines.launch

class AuthViewModel:ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun login(email: String, password: String) = viewModelScope.launch {
        UserRepo.login(email, password)?.let {
            _user.postValue(it.user)
        }
    }

    fun signup(username: String, email: String, password: String) = viewModelScope.launch {
        UserRepo.signup(username, email, password)?.let {
            _user.postValue(it.user)
        }
    }

    fun update(bio: String?, username: String?, image: String?, email: String?, password: String?) = viewModelScope.launch{
        UserRepo.updateUser(bio, username, image, email, password)?.let {
            _user.postValue(it.user)
        }
    }

    fun getCurrentUser(token:String)=viewModelScope.launch {
        UserRepo.getCurrentUser(token)?.let{
            _user.postValue(it)
        }
    }

    fun logout() {
        _user.postValue(null)
    }

}