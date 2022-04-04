package com.example.basic_3_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainData(val title: String, val content:String, val image: String): Parcelable
