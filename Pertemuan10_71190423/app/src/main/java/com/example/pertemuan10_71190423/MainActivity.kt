package com.example.pertemuan10_71190423

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var dbHelper : DatabaseHelper? = null
    var sql : SQLiteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName = findViewById<EditText>(R.id.name)
        val edtAge = findViewById<EditText>(R.id.Age)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnDel = findViewById<Button>(R.id.btnDelete)
        val searchBtn = findViewById<Button>(R.id.searchBtn)

        dbHelper = DatabaseHelper(this)
        sql = dbHelper?.writableDatabase

        btnSave.setOnClickListener {
            val values = ContentValues().apply {
                put(DatabaseContract.Penduduk.COLUMN_NAME, edtName.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_AGE, edtAge.text.toString())
            }
            sql?.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)
            edtName.setText("")
            edtAge.setText("")

            refreshData()
        }

        btnDel.setOnClickListener {
            val selection = "USIA = ? OR NAMA = ?"
            val selectionArgs = arrayOf(edtAge.text.toString(), edtName.text.toString())
            sql?.delete(DatabaseContract.Penduduk.TABLE_NAME, selection, selectionArgs)
            refreshData()
        }
        refreshData()

        searchBtn.setOnClickListener {
            if((edtName.text.toString() == "" && edtAge.text.toString() == "") ||
                (edtName.text.toString() == null && edtAge.text.toString() == null)){
                refreshData()
            }
            else{
                searchData(edtName.text.toString(), edtAge.text.toString())

            }
        }
        refreshData()

    }

    fun refreshData(){
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val column = arrayOf(BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAME,
            DatabaseContract.Penduduk.COLUMN_AGE)

        val cursor = sql?.query(DatabaseContract.Penduduk.TABLE_NAME,
            column,
            null,
            null,
            null,
            null,
            null)

        var result = ""
        with(cursor){
            while (this!!.moveToNext()){
                result += "${this!!.getString(1)}-${this!!.getString(2)}th\n"
            }
        }
        txtResult.text = result

    }

    @SuppressLint("Range")
    fun searchData(name : String, age : String){
        val dbReader = dbHelper?.readableDatabase
        val columns = arrayOf(BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAME,
            DatabaseContract.Penduduk.COLUMN_AGE
        )

        val selector = "${DatabaseContract.Penduduk.COLUMN_NAME} LIKE ? OR " +
                "${DatabaseContract.Penduduk.COLUMN_AGE} LIKE ?"
        val seletorArgs = arrayOf(name, age)
        val sort = "${DatabaseContract.Penduduk.COLUMN_NAME} ASC "
        val cursor = dbReader?.query(DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            selector,
            seletorArgs,
            null,
            null,
            sort)

        var result = ""
        with(cursor!!){
            while (moveToNext()){
                result += "${getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME))}"+
                        "-${getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_AGE))}th\n"
            }
            val txtResult = findViewById<TextView>(R.id.txtResult)
            txtResult.text = result

        }
        cursor.close()
    }
}