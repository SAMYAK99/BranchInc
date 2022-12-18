package com.projects.trending.branchinternational.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.trending.branchinternational.model.MessageRequest
import com.projects.trending.branchinternational.model.MessagesItem
import com.projects.trending.branchinternational.network.ApiUtilites
import com.projects.trending.branchinternational.utils.PreferenceData
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    val messageData : MutableLiveData<Response<ArrayList<MessagesItem>>> = MutableLiveData()
    val chatData : MutableLiveData<Response<MessagesItem>> = MutableLiveData()


    fun getMessages(context: Context){
       val token = PreferenceData.getLoggedInUserUid(context)
//        println("TOKEN" + token)
        viewModelScope.launch {
            val res = ApiUtilites.service.getMessages(token!!)
            messageData.value = res
        }
    }

    fun updateChat(context: Context , mRequest : MessageRequest){
        val token = PreferenceData.getLoggedInUserUid(context)
//        println("TOKEN" + token)
        viewModelScope.launch {
            val res = ApiUtilites.service.postMessage(token!! , mRequest)
            chatData.value = res
        }
    }



}