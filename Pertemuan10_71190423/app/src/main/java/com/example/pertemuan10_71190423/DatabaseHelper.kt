package com.example.pertemuan10_71190423

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        val DATABASE_NAME = "myDB"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(sql: SQLiteDatabase?) {
        sql?.execSQL(DatabaseContract.Penduduk.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(sql: SQLiteDatabase?, p1: Int, p2: Int) {
        sql?.execSQL(DatabaseContract.Penduduk.SQL_DELETE_TABLE)
        onCreate(sql)
    }
}