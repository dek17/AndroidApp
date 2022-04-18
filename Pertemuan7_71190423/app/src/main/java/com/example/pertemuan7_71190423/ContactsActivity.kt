package com.example.pertemuan7_71190423

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_contact)

        val imageContact = findViewById<ImageView>(R.id.photos)
        val namesContact = findViewById<TextView>(R.id.name)
        val numberContact = findViewById<TextView>(R.id.telephone)
        val whatsappName = findViewById<TextView>(R.id.whatsapp)
        val lineID = findViewById<TextView>(R.id.line)
        val gmailID = findViewById<TextView>(R.id.gmail)

        val bundle : Bundle? = intent.extras
        val imageId = bundle!!.getInt("imageId")
        val titleName = bundle.getString("titleName")
        val phoneNumber = bundle.getString("phoneNumber")
        val waName = bundle.getString("waName")
        val lineId = bundle.getString("lineId")
        val gMail = bundle.getString("gmailName")


        imageContact.setImageResource(imageId)
        namesContact.text = titleName
        numberContact.text = phoneNumber
        whatsappName.text = waName
        lineID.text = lineId
        gmailID.text = gMail


        val backButton = findViewById<androidx.appcompat.widget.AppCompatImageButton>(R.id.buttonBack)
        backButton.setOnClickListener {
            val Back = Intent(this, MainActivity::class.java)
            startActivity(Back)
        }





    }

}