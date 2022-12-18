package com.projects.trending.branchinternational.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.trending.branchinternational.adapters.MessageAdapter
import com.projects.trending.branchinternational.databinding.ActivityMessageBinding
import com.projects.trending.branchinternational.model.MessagesItem
import com.projects.trending.branchinternational.utils.PreferenceData.clearLoggedInUserId
import com.projects.trending.branchinternational.viewmodels.MainViewModelFactory
import com.projects.trending.branchinternational.viewmodels.MainViewModel


class MessageActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMessageBinding
    private lateinit var messAdapter: MessageAdapter
    private lateinit var list: MutableList<MessagesItem>
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enabling view binding
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        list = ArrayList()
        prepareRecyclerView()
        fetchMessages()


        binding.imgLogout.setOnClickListener{
            clearLoggedInUserId(this)
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
    }


    private fun fetchMessages() {


        viewModel.getMessages(this)

        viewModel.messageData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val questions = response.body()
                list.addAll(questions!!)
                list.sortByDescending  { it.timestamp }
                messAdapter!!.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

        private fun prepareRecyclerView() {

            messAdapter = MessageAdapter(list, context = this)
            binding.rvMessages.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = messAdapter
            }
        }

    }
