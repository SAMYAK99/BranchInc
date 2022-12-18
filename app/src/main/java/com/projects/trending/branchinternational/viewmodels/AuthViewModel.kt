package com.projects.trending.branchinternational.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.trending.branchinternational.model.Auth
import com.projects.trending.branchinternational.model.UserRequest
import com.projects.trending.branchinternational.network.ApiUtilites
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel : ViewModel() {

    private val _loginResponse : MutableLiveData<Response<Auth>> = MutableLiveData()
    val loginResponse : LiveData<Response<Auth>>
      get() = _loginResponse


    fun login(name : String , pass : String) = viewModelScope.launch {
        val response = ApiUtilites.service.loginUser(UserRequest(name, pass))
        _loginResponse.value = response
    }
}