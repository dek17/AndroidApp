package com.example.a71190423_final

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.add_edit_activity.*
import java.util.*

class AddEditActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_activity)

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val googleClient = GoogleSignIn.getClient(this, signInOptions)
        val acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

        firestore = FirebaseFirestore.getInstance()
        if (TitleInput!=null && WriterInput!=null && CreatorInput!=null && YearsInput!=null){
            btnSave.setOnClickListener {

                if (TitleInput!=null && WriterInput!=null && CreatorInput!=null && YearsInput!=null){
                    if (acct!=null){
                        val personName = acct.email
                        val bookUser = Users(TitleInput.text.toString(), WriterInput.text.toString(), CreatorInput.text.toString(),
                            YearsInput.text.toString().toInt(), personName.toString(), PageInput.toString() )
                        if (firestore?.collection(personName.toString())
                                ?.document(TitleInput.text.toString()) == null){
                            Toast.makeText(this, "Book has been added", Toast.LENGTH_LONG).show()
                        }
                        else{
                            firestore?.collection(personName.toString())?.document(bookUser.title)
                                ?.set(bookUser)
                                ?.addOnSuccessListener {
                                    val i = Intent(this, MainActivity::class.java)
                                    startActivity(i)
                                    finish()
                                    Toast.makeText(this, "Save Successfully!", Toast.LENGTH_LONG
                                    ).show()
                                }?.
                                addOnFailureListener { Toast.makeText(this, "Unsuccessfully to Save!",
                                    Toast.LENGTH_LONG).show()
                                }
                        }
                    }
                }
                else{
                    Toast.makeText(this, "Complete All Data!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}







}
