package com.example.a71190423_final

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.book_detail.*

class BookDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_detail)

        val getTitle = intent.getStringExtra("title")
        val getWriter = intent.getStringExtra("writer")
        val getCreator = intent.getStringExtra("creator")
        val getYears = intent.getStringExtra("years")
        val getPage= intent.getStringExtra("page")

        val bundle : Bundle = intent.extras!!


        titleDesc.text = getTitle
        writerDesc.text = getWriter
        creatorDesc.text = getCreator
        yearDesc.text = getYears
        pageDesc.text = getPage

        backBtn.setOnClickListener {
            val back = Intent(this, MainActivity::class.java )
            startActivity(back)
            finish()
        }




    }
}