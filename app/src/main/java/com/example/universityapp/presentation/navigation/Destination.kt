package com.example.universityapp.presentation.navigation

sealed class Destination(val route: String) {
    object HomeScreen : Destination(route = "main")
}