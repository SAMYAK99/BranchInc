package com.projects.trending.branchinternational.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.projects.trending.branchinternational.R
import com.projects.trending.branchinternational.utils.PreferenceData.getLoggedInUserUid


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Switching to next screen
        Handler().postDelayed({
            if (getLoggedInUserUid(this@MainActivity)!!.isEmpty()) {
                val mainIntent = Intent(this@MainActivity, LoginActivity::class.java)
                this@MainActivity.startActivity(mainIntent)
            } else {
                val mainIntent = Intent(this@MainActivity, MessageActivity::class.java)
                this@MainActivity.startActivity(mainIntent)
            }

            finish()
        },3000)

    }
}