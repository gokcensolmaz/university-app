package com.example.universityapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.universityapp.presentation.navigation.Navigation
import com.example.universityapp.presentation.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.iconView.animate().setDuration(100).alpha(0f).withEndAction {
                splashScreenViewProvider.remove()
            }
        }
        setTheme(R.style.Theme_Universityapp)
        setContent {
                val navController = rememberNavController()
                Navigation(navigationHandler = NavigationHandler(navController))

        }
    }
}
