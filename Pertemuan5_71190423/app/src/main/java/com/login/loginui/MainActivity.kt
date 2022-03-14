package com.login.loginui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.os.Bundle
import android.os.Message
import android.view.Window
import android.view.WindowManager
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName: com.google.android.material.textfield.TextInputEditText = findViewById(R.id.userName)
        val password = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.password)
        val Login = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.Login)
        Login.setOnClickListener {
            login(userName.text.toString(), password.text.toString() )
        }
    }

    fun login(username: String, password: String){
        if(password.equals("nanamucupucu")) {
            val i: Intent = Intent(this, LoggedActivity::class.java)
            i.putExtra("name", username)
            startActivity(i)
        }else if(username.isBlank() && password.isBlank()){
            showMessage("Please Write Your Username and Password!")

        }else if(username.isNotBlank() && password.isBlank()){
            showMessage("Please write your Password")

        }else if(username.isBlank() && password.isNotBlank()){
            showMessage("Please write your Username!")
        }else{
            showMessage("Wrong Password!")
        }
    }
    fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }





}