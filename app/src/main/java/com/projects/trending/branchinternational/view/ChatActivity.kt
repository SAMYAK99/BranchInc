package com.projects.trending.branchinternational.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.trending.branchinternational.adapters.ChatAdapter
import com.projects.trending.branchinternational.adapters.MessageAdapter
import com.projects.trending.branchinternational.databinding.ActivityChatBinding
import com.projects.trending.branchinternational.model.MessageRequest
import com.projects.trending.branchinternational.model.MessagesItem
import com.projects.trending.branchinternational.network.ApiUtilites
import com.projects.trending.branchinternational.utils.PreferenceData
import com.projects.trending.branchinternational.viewmodels.MainViewModel
import com.projects.trending.branchinternational.viewmodels.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var list: ArrayList<MessagesItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enabling View Binding
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Getting the token and list from previous activity using Intent
        val threadId = intent.getStringExtra("thread")
        list  = intent.getSerializableExtra("data") as ArrayList<MessagesItem>


        prepareRecyclerView(list)


        val viewModelFactory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        // Observing the live data
        viewModel.chatData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                list.add(response.body()!!)
                list.sortByDescending { it.timestamp }
                chatAdapter!!.notifyDataSetChanged()
                // Displaying Toast Message
                Toast.makeText(this, "Message Sent Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Api Error, Please Try Again Later", Toast.LENGTH_SHORT).show()
            }
        })

        // On click listners on send button
        binding.btnSend.setOnClickListener{
            val message = binding.etMessage.text.toString()
            if(message.isEmpty()){
                Toast.makeText(this, "Please Enter any Message", Toast.LENGTH_SHORT).show()
            }
            else {
                updateChat(threadId!!, message)
                binding.etMessage.text.clear()
            }
        }

    }

    private fun updateChat(thread : String , message :String) {
        viewModel.updateChat(this, MessageRequest(thread,message))
    }

    private fun prepareRecyclerView(list : ArrayList<MessagesItem>) {
        chatAdapter = ChatAdapter(list, context = this)
        binding.rvChats.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@ChatActivity, MessageActivity::class.java)
        startActivity(intent)
    }
}