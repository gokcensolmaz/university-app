package com.example.universityapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.universityapp.data.local.University
import com.example.universityapp.presentation.home.HomeScreen
import com.example.universityapp.presentation.home.HomeViewModel
import com.example.universityapp.presentation.webview.WebViewScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value


    NavHost(navController = navController, startDestination = Destination.HomeScreen.route) {
        composable(Destination.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val cities = viewModel.cities.collectAsLazyPagingItems()
            HomeScreen(
                modifier = modifier,
                cities = cities
            )
        }
        composable(Destination.WebViewScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<University?>("university")
                ?.let { university ->
                    WebViewScreen(
                        url = university.website
                    )
                }
        }
    }

}
fun navigateToWebView(navController: NavController, university: University) {
    navController.currentBackStackEntry?.savedStateHandle?.set("university", university)
    navController.navigate(Destination.WebViewScreen.route)
}