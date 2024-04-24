package com.example.universityapp.domain.model

data class City(
    val id: Int,
    val province: String,
    val universities: List<University>
)