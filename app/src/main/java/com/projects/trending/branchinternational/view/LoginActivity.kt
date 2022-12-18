package com.projects.trending.branchinternational.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.projects.trending.branchinternational.databinding.ActivityLoginBinding
import com.projects.trending.branchinternational.model.UserRequest
import com.projects.trending.branchinternational.network.ApiUtilites
import com.projects.trending.branchinternational.utils.PreferenceData
import com.projects.trending.branchinternational.viewmodels.AuthViewModel
import com.projects.trending.branchinternational.viewmodels.AuthViewModelFactory
import com.projects.trending.branchinternational.viewmodels.MainViewModel
import com.projects.trending.branchinternational.viewmodels.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // Enabling View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instantiating View Model
        val viewModelFactory = AuthViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)


       // Observing view model after making api call
        viewModel.loginResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val token = response.body()!!.authToken
                PreferenceData.setLoggedInUserUid(this@LoginActivity, token)
                val intent = Intent(this@LoginActivity, MessageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Api Error, Please Try Again Later", Toast.LENGTH_SHORT).show()
            }
        })

        // On click listner for sign in button
        binding.loginSignIn.setOnClickListener {
            val name = binding.loginEmail.text.toString()
            val pass = binding.loginPassword.text.toString()

            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(
                    this, "Email Or Password Can't be " +
                            "Empty", Toast.LENGTH_SHORT
                ).show();
            } else {
                viewModel.login(name,pass)
            }
        }
    }
}