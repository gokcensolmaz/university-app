package com.example.universityapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class University(
    val adress: String,
    val email: String,
    val fax: String,
    @PrimaryKey val name: String,
    val phone: String,
    val rector: String,
    val website: String
)