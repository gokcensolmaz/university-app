package com.example.universityapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.universityapp.presentation.navigation.Navigation
import com.example.universityapp.presentation.navigation.NavigationHandler
import com.example.universityapp.ui.theme.UniversityappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityappTheme {
                val navController = rememberNavController()
                Navigation(navigationHandler = NavigationHandler(navController))
            }
        }
    }
}
