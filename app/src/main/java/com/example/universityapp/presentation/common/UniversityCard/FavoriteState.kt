package com.example.universityapp.presentation.common.UniversityCard

import com.example.universityapp.data.local.University

data class FavoriteState (
    val universities: List<University> = emptyList()
)