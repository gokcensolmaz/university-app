package com.example.universityapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.universityapp.presentation.home.HomeScreen
import com.example.universityapp.presentation.home.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value

    Scaffold(modifier = Modifier.fillMaxSize()){
        NavHost(navController = navController, startDestination = Destination.HomeScreen.route ){
            composable(Destination.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val cities = viewModel.cities.collectAsLazyPagingItems()
                HomeScreen(
                    cities = cities
                )
            }
        }
    }
}