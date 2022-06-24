package com.example.a71190423_final

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import com.example.a71190423_final.bind.UserAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.google.android.gms.auth.api.signin.GoogleSignIn.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.firestore.auth.User
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    lateinit var listBook: ArrayList<Users>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleClient = getClient(this@MainActivity, gso)
        val acc: GoogleSignInAccount? = getLastSignedInAccount(this)
        firestore = FirebaseFirestore.getInstance()


        addButton.setOnClickListener {
            val add = Intent(this, AddEditActivity::class.java)
            startActivity(add)
        }

        btnLogout.setOnClickListener {
            googleClient.signOut().addOnCompleteListener(this) {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }


        if (acc!=null){
            val personName = acc.email
            if (personName != null) {
                firestore?.collection(personName)
                    ?.get()
                    ?.addOnSuccessListener {documents ->
                        listBook = arrayListOf<Users>()
                        listBook.clear()
                        for (document in documents){
                            listBook.add(Users(
                                document["title"].toString(),
                                document["writer"].toString(),
                                document["creator"].toString(),
                                document["years"].toString().toInt(),
                                document["account"].toString(),
                                document["pageTotal"].toString()
                            ))
                        }


                        recycle_view.layoutManager=LinearLayoutManager(this@MainActivity)
                        val adapter = UserAdapter(listBook, this@MainActivity)
                        recycle_view.adapter=adapter

                        searchView.clearFocus()
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(text: String): Boolean {
                                listBook.clear()
                                for (document in documents) {
                                    if (document["title"].toString().toLowerCase()
                                            .contains(text.toLowerCase()) ||
                                        document["writer"].toString().toLowerCase()
                                            .contains(text.toLowerCase()) ||
                                        document["creator"].toString().toLowerCase()
                                            .contains(text.toLowerCase()) ||
                                        document["years"].toString().toLowerCase()
                                            .contains(text.toLowerCase()) ||
                                        document["pageTotal"].toString().toLowerCase()
                                            .contains(text.toLowerCase()) ||
                                        document["account"].toString().toLowerCase()
                                            .contains(text.toLowerCase())
                                    ) {
                                        listBook.add(Users(
                                            document["title"].toString(),
                                            document["writer"].toString(),
                                            document["creator"].toString(),
                                            document["years"].toString().toInt(),
                                            document["pageTotal"].toString(),
                                            document["account"].toString()
                                        ))
                                    }
                                }


                                recycle_view.layoutManager = LinearLayoutManager(this@MainActivity)
                                val adapter = UserAdapter(listBook, this@MainActivity)
                                recycle_view.adapter = adapter
                                return true
                            }


                        })
                    }

    }
            }
        }
    }








