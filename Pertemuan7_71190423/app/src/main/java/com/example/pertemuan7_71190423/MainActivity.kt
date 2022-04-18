package com.example.pertemuan7_71190423

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Contacts>
    private lateinit var temptArrayList: ArrayList<Contacts>
    lateinit var imageId : Array<Int>
    lateinit var titleName : Array<String>
    lateinit var phoneNumber : Array<String>
    lateinit var lineId : Array<String>
    lateinit var waName : Array<String>
    lateinit var gmailName : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.recycler)

        imageId = arrayOf(R.drawable.avatarmenone,
                            R.drawable.avatarmenold,
                            R.drawable.avatarwomentwo,
                            R.drawable.avatarmenyoung)

        titleName = arrayOf("Molten","John","Maria","Bradt")
        phoneNumber = arrayOf("+621325472023","+625932332544","+628229363233","+62179441237")
        waName = arrayOf("Molten Ferreira", "Henry John","Ve Maria","Hilary Bradt")
        lineId = arrayOf("@maxwellMolten", "@denverJohn","@veMaria","@bradter")
        gmailName = arrayOf("molten71@gmail.com","johnhen@gmail.com","vemaria@gmail.com","bradter08@gmail.com")


        newRecyclerView = findViewById(R.id.rcyView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Contacts>()
        temptArrayList = arrayListOf<Contacts>()
        getUserData()
    }

    private fun getUserData() {
        for (i in imageId.indices){
            val contact = Contacts(imageId[i],titleName[i],phoneNumber[i])
            newArrayList.add(contact)
        }

        temptArrayList.addAll(newArrayList)
        val adapter = Adapter(temptArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : Adapter.onItemClickList{
            override fun OnItemClick(position: Int) {
                val i = Intent(this@MainActivity, ContactsActivity::class.java)
                i.putExtra("imageId", newArrayList[position].Photos)
                i.putExtra("titleName", newArrayList[position].Names)
                i.putExtra("phoneNumber", newArrayList[position].Phones)
                i.putExtra("lineId", lineId[position])
                i.putExtra("waName",waName[position])
                i.putExtra("gmailName",gmailName[position])
                startActivity(i)

            }
        })


    }
}