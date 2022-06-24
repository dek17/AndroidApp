package com.example.a71190423_final

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.update_activity.*
import java.util.*

class EditActivity : AppCompatActivity(){
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_activity)

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this, signInOptions)
        val acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

        firestore = FirebaseFirestore.getInstance()

        val getTitle = intent.getStringExtra("title")
        val getWriter = intent.getStringExtra("writer")
        val getCreator = intent.getStringExtra("creator")
        val getYears = intent.getStringExtra("years")
        val getPage = intent.getStringExtra("page")

        val bundle : Bundle = intent.extras!!
        titleUpdate.setText(getTitle)
        writerUpdate.setText(getWriter)
        creatorUpdate.setText(getCreator)
        yearsUpdate.setText(getYears)
        pageUpdate.setText(getPage)

        backBtnEdit.setOnClickListener {
            val backUpdate = Intent(this, MainActivity::class.java)
            startActivity(backUpdate)
            finish()
        }

        saveUpdate.setOnClickListener {
            if (titleUpdate !=null &&  writerUpdate !=null && creatorUpdate !=null
                && yearsUpdate != null && pageUpdate !=null){
                if (acct!=null){
                    val personName = acct.email
                    val usersBook = Users(titleUpdate.text.toString(), writerUpdate.text.toString(),
                        creatorUpdate.text.toString(), yearsUpdate.text.toString().toInt(),
                        personName.toString(), pageUpdate.text.toString())
                    firestore?.collection(personName.toString())
                        ?.document(titleUpdate.text.toString())
                        ?.set(usersBook)
                        ?.addOnSuccessListener {
                            val i = Intent(this, MainActivity::class.java)
                            startActivity(i)
                            finish()
                            Toast.makeText(
                                this, "Successfully Update !", Toast.LENGTH_SHORT).show()
                        }?.addOnFailureListener {
                            Toast.makeText(this, "Failed to Update!", Toast.LENGTH_LONG)
                                .show()
                        }
                }
            }
            else{
                Toast.makeText(this, "Input All Data !", Toast.LENGTH_LONG).show()
            }

        }



    }
}