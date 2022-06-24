package com.example.a71190423_final

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class StartActivity : AppCompatActivity() {
    private lateinit var LogAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        LogAuth = FirebaseAuth.getInstance()
        val user = LogAuth.currentUser

        Handler().postDelayed({
            if(user!= null){
                val dashboardInt = Intent(this, MainActivity::class.java)
                startActivity(dashboardInt)
                finish()

            }else{
                val signInInt = Intent(this, LoginActivity::class.java)
                startActivity(signInInt)
                finish()
            }

        }, 2000)


    }
}