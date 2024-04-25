package com.example.universityapp.domain.model

import com.example.universityapp.data.local.University

data class City(
    val id: Int,
    val province: String,
    val universities: List<University>
)