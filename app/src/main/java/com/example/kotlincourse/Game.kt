package com.example.kotlincourse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Game (val name: String, val description: String, val link: String, val img: String): Parcelable
