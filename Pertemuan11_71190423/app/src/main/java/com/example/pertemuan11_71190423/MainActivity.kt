package com.example.pertemuan11_71190423

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    var SharedPref : SharedPreferences? = null
    var SPedit : SharedPreferences.Editor? = null
    lateinit var locale : Locale
    var currentPost = " "
    var currentPostStr : String? = null
    var arrayList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName = findViewById<EditText>(R.id.name)
        val edtNim = findViewById<EditText>(R.id.nim)
        val edtIPK = findViewById<EditText>(R.id.IPK)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnDel = findViewById<Button>(R.id.btnDelete)
        val btnSearch = findViewById<Button>(R.id.searchBtn)

        SharedPref = getSharedPreferences("SP", MODE_PRIVATE)
        SPedit = SharedPref?.edit()
        val LastClick = SharedPref?.getInt("LastClick", 0)
        currentPost = intent.getStringExtra(currentPostStr).toString()




        btnSave.setOnClickListener {
            val mahasiswa = Mahasiswa(edtName.text.toString(),
                edtNim.text.toString(),
                edtIPK.text.toString())
            db.collection("mahasiswa").add(mahasiswa)
            edtName.setText("")
            edtNim.setText("")
            edtIPK.setText("")
            refreshData()

        }
        refreshData()

//
//        btnDel.setOnClickListener {
//
//        }

        btnSearch.setOnClickListener {
            val searchName = edtName.text.toString()
            val searchNim = edtNim.text.toString()
            val searchIpk = edtIPK.text.toString()

            if (searchName.isBlank() && searchNim.isBlank() && searchIpk.toString().isBlank()) {
                Toast.makeText(this, "Please write in one or more column ", Toast.LENGTH_LONG).show()

            } else {
                if (searchName.isNotBlank() && searchNim.isBlank() && searchIpk.isBlank()) {
                    searchNAME(searchName)  /*Search by Name*/
                } else if (searchName.isBlank() && searchNim.isNotBlank() && searchIpk.isBlank()) {
                    searchNIM(searchNim) /*Search by Nim*/
                } else if (searchName.isBlank() && searchNim.isBlank() && searchIpk.isNotBlank()) {
                    searchIPK(searchIpk) /*Search by Ipk*/
                } else if (searchName.isNotBlank() && searchNim.isNotBlank() && searchIpk.toString().isBlank()) {
                    searchNAMENIM(searchName, searchNim) /*Search by Name Nim*/
                } else if (searchName.isNotBlank() && searchNim.isNotBlank() && searchIpk.isNotBlank()) {
                    search(searchName, searchNim, searchIpk)  /*Search by Name*/ /*Search by Nim*/ /*Search by Ipk*/
                } else if (searchName.isNotBlank() && searchNim.isBlank() && searchIpk.toString().isNotBlank()) {
                    searchNAMEIPK(searchName, searchIpk) /*Search by Name Ipk*/
                } else if (searchName.isBlank() && searchNim.isNotBlank() && searchIpk.toString().isNotBlank()) {
                    searchNIMIPK(searchNim, searchIpk) /*Search by Nim Ipk*/
                }
            }
        }

//                Spinner Sorting
        val sorting = resources.getStringArray(R.array.sorter)
        val sortSpinner = findViewById<Spinner>(R.id.sortSpinner)
//
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sorting)
        sortSpinner.adapter = adapter
//        sortSpinner.setSelection(LastClick!!)
        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val searchName = db.collection("mahasiswa").orderBy("name").toString()
                val searchNim = db.collection("mahasiswa").orderBy("nim").toString()
                val searchIpk = db.collection("mahasiswa").orderBy("ipk").toString()

//                SPedit?.commit()
                when(position){
                    0 -> searchAsc("Nama")
                    1 -> searchDesc("Nama")
                    2 -> searchAsc("Nim")
                    3 -> searchDesc("Nim")
                    4 -> searchAsc("Ipk")
                    5 -> searchDesc("Ipk")


                }
//                Get Position Save
//                SPedit?.putInt("LastClick", position)
//                if (parent != null) {
//                    if (parent.getItemAtPosition(position) == "Name Ascending"){
//                            searchAscName(searchName)
//                    }
//                    else if (parent.getItemAtPosition(position) == "Name Descending"){
//                            searchDescName(searchName, searchNim, searchIpk)
//                    }
//                    else if (parent.getItemAtPosition(position) == "Nim Ascending"){
//                        searchAscNim(searchName, searchNim, searchIpk)
//                    }
//                    else if (parent.getItemAtPosition(position) == "Nim Descending"){
//                        searchDescNim(searchName, searchNim, searchIpk)
//                    }
//                    else if (parent.getItemAtPosition(position) == "Ipk Ascending"){
//                        searchAscIpk(searchName, searchNim, searchIpk)
//                    }
//                    else if (parent.getItemAtPosition(position) == "Ipk Descending"){
//                        searchDescIpk(searchName, searchNim, searchIpk)
//                    }
//                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }

    fun refreshData() {
        db.collection("mahasiswa").get().addOnSuccessListener { result ->
            val txtResult = findViewById<TextView>(R.id.txtResult)
            var hasil = ""
            for (doc in result) {
                hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"
            }
            txtResult.setText(hasil)
        }
    }

    fun searchNAME(name : String) {
            db.collection("mahasiswa").whereEqualTo("nama", name).get()
                .addOnSuccessListener { snapshot ->
                    val txtResult = findViewById<TextView>(R.id.txtResult)
                    var hasil = ""
                    for (doc in snapshot) {
                        hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"

                    }
                    if (hasil.isBlank()){
                        Toast.makeText(this, "Invalid Search Name", Toast.LENGTH_LONG).show()
                        hasil += "Can't find Data"
                    }
                    txtResult.setText(hasil)

                }
        }

    fun searchNIM(nim: String) {
        db.collection("mahasiswa").whereEqualTo("nim", nim).get()
            .addOnSuccessListener { snapshot ->
                val txtResult = findViewById<TextView>(R.id.txtResult)
                var hasil = ""
                for (doc in snapshot) {
                    hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"

                }
                if (hasil.isBlank()){
                    Toast.makeText(this, "Invalid Search NIM", Toast.LENGTH_LONG).show()
                    hasil += "Can't find Data"
                }
                txtResult.setText(hasil)

            }
    }

    fun searchIPK(ipk : String) {
        db.collection("mahasiswa").whereEqualTo("ipk", ipk).get()
            .addOnSuccessListener { snapshot ->
                val txtResult = findViewById<TextView>(R.id.txtResult)
                var hasil = ""
                for (doc in snapshot) {
                    hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"

                }
                if (hasil.isBlank()){
                    Toast.makeText(this, "Invalid Search IPK", Toast.LENGTH_LONG).show()
                    hasil += "Can't find Data"
                }
                txtResult.setText(hasil)

            }
    }

    fun searchNAMENIM(name : String, nim : String) {
        db.collection("mahasiswa").whereEqualTo("nama", name).
        whereEqualTo("nim", nim).get()
            .addOnSuccessListener { snapshot ->
                val txtResult = findViewById<TextView>(R.id.txtResult)
                var hasil = ""
                for (doc in snapshot) {
                    hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"

                }
                if (hasil.isBlank()){
                    Toast.makeText(this, "Invalid Search Name & Nim", Toast.LENGTH_LONG).show()
                    hasil += "Can't find Data"
                }
                txtResult.setText(hasil)

            }
    }

    fun searchNAMEIPK(name : String, ipk : String) {
        db.collection("mahasiswa").whereEqualTo("nama", name).
        whereEqualTo("ipk", ipk).get()
            .addOnSuccessListener { snapshot ->
                val txtResult = findViewById<TextView>(R.id.txtResult)
                var hasil = ""
                for (doc in snapshot) {
                    hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"

                }
                if (hasil.isBlank()){
                    Toast.makeText(this, "Invalid Search Name & IPK", Toast.LENGTH_LONG).show()
                    hasil += "Can't find Data"
                }
                txtResult.setText(hasil)

            }
    }

    fun searchNIMIPK(nim : String, ipk : String) {
        db.collection("mahasiswa").whereEqualTo("nim", nim).
        whereEqualTo("ipk", ipk).get()
            .addOnSuccessListener { snapshot ->
                val txtResult = findViewById<TextView>(R.id.txtResult)
                var hasil = ""
                for (doc in snapshot) {
                    hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"

                }
                if (hasil.isBlank()){
                    Toast.makeText(this, "Invalid Search NIM IPK", Toast.LENGTH_LONG).show()
                    hasil += "Can't find Data"
                }
                txtResult.setText(hasil)

            }
    }


    fun search(name : String, nim : String, ipk : String) {
        db.collection("mahasiswa").whereEqualTo("nama", name).
        whereEqualTo("nim", nim).whereEqualTo("ipk", ipk).get()
            .addOnSuccessListener { snapshot ->
                val txtResult = findViewById<TextView>(R.id.txtResult)
                var hasil = ""
                for (doc in snapshot) {
                    hasil += "${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n"

                }
                if (hasil.isBlank()){
                    Toast.makeText(this, "Invalid Search All", Toast.LENGTH_LONG).show()
                    hasil += "Can't find Data"
                }
                txtResult.setText(hasil)

            }
    }


    fun searchAsc(name : String) {
        db.collection("mahasiswa").get().addOnSuccessListener { result ->
            val txtResult = findViewById<TextView>(R.id.txtResult)
            var hasil = ""
            var arrayAsc = ArrayList<String>()
            var arrayAsc1 = ArrayList<String>()
            for (doc in result) {
                if (name == "Nama"){
                    arrayAsc.add("${doc.get("nama")}")
                }
                else if (name == "Nim"){
                    arrayAsc.add("${doc.get("nim")}")
                }
                else if (name == "Ipk"){
                    arrayAsc.add("${doc.get("ipk")}")
                }
                arrayList.add("${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n")
            }
            val arraySorted = arrayAsc.sorted()
            for (item in arraySorted) {
                for (item1 in arrayList) {
                    if (item1.contains(item)) {
                        arrayAsc1.add(item1)
                        arrayList.remove(item1)
                        break
                    }
                }
            }
            arrayList.clear()
            arrayList = arrayAsc1
            for (cetak in arrayList){
                hasil += cetak
            }
            txtResult.setText(hasil)
        }
    }

    fun searchDesc(name : String) {
        db.collection("mahasiswa").get().addOnSuccessListener { result ->
            val txtResult = findViewById<TextView>(R.id.txtResult)
            var hasil = ""
            var arrayDesc = ArrayList<String>()
            var arrayDesc1 = ArrayList<String>()
            for (doc in result) {
                if (name == "Nama"){
                    arrayDesc.add("${doc.get("nama")}")
                }
                else if (name == "Nim"){
                    arrayDesc.add("${doc.get("nim")}")
                }
                else if (name == "Ipk"){
                    arrayDesc.add("${doc.get("ipk")}")
                }
                arrayList.add("${doc.get("nama")}-${doc.get("nim")}-${doc.get("ipk")}\n")
            }
            val arraySorted = arrayDesc.sortedDescending()
            for (item in arraySorted) {
                for (item1 in arrayList) {
                    if (item1.contains(item)) {
                        arrayDesc1.add(item1)
                        arrayList.remove(item1)
                        break
                    }
                }
            }
            arrayList.clear()
            arrayList = arrayDesc1
            for (cetak in arrayList){
                hasil += cetak
            }
            txtResult.setText(hasil)
        }
    }



}






