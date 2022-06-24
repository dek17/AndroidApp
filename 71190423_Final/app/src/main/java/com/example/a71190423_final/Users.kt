package com.example.a71190423_final

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Users (
                   val title : String,
                   val writer : String,
                   val creator : String,
                   val years : Int,
                   val account : String,
                   val pageTotal : String
                )