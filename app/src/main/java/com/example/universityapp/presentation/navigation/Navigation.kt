package com.example.universityapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.universityapp.data.local.University
import com.example.universityapp.presentation.common.UniversityTopAppBar
import com.example.universityapp.presentation.favorite.FavoriteScreen
import com.example.universityapp.presentation.favorite.FavoriteViewModel
import com.example.universityapp.presentation.home.HomeScreen
import com.example.universityapp.presentation.home.HomeViewModel
import com.example.universityapp.presentation.webview.WebViewScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    // TODO: Top bar title
    var topAppBarTitle by remember { mutableStateOf("Üniversiteler") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UniversityTopAppBar(
                title = topAppBarTitle,
                navigateToFavorite = {
                    navigateToFavorite(
                        navController = navController,
                        route = Destination.FavoriteScreen.route
                    )
                },
                navigateUp = { navController.navigateUp() })
        },

        ) {
        val topPadding = it.calculateTopPadding()

        NavHost(
            modifier = Modifier.padding(top = topPadding),
            navController = navController,
            startDestination = Destination.HomeScreen.route
        ) {
            composable(Destination.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                topAppBarTitle = "Üniversiteler"
                val cities = viewModel.cities.collectAsLazyPagingItems()
                HomeScreen(
                    cities = cities
                )
            }
            composable(Destination.FavoriteScreen.route) {
                val viewModel: FavoriteViewModel = hiltViewModel()
                val state = viewModel.state.value
                topAppBarTitle = "Favorilerim"
                FavoriteScreen(
                    state = state
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


}

private fun navigateToFavorite(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

fun navigateToWebView(navController: NavController, university: University) {
    navController.currentBackStackEntry?.savedStateHandle?.set("university", university)
    navController.navigate(Destination.WebViewScreen.route)
}