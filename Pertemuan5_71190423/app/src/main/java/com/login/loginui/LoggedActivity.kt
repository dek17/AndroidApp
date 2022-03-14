package com.login.loginui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoggedActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        val username = intent.getStringExtra("name")
        val welcome = findViewById<TextView>(R.id.welcome)
        welcome.text = "WELCOME ${username} "
        val logoutButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            val start = Intent(this, MainActivity::class.java)
            startActivity(start)
        }

    }

}