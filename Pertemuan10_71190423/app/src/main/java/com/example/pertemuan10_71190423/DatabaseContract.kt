package com.example.pertemuan10_71190423

import android.provider.BaseColumns

class DatabaseContract {
    object Penduduk : BaseColumns {
        val TABLE_NAME = "penduduk"
        val COLUMN_NAME = "Nama"
        val COLUMN_AGE = "Usia"

        val SQL_CREATE_TABLE = """CREATE TABLE ${TABLE_NAME} (
            ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${COLUMN_NAME} TEXT,
            ${COLUMN_AGE} INTEGER)
        """.trimMargin()

        val SQL_DELETE_TABLE = "DROP TABLE IF EXIST ${TABLE_NAME}"
    }
}