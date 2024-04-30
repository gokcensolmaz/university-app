package com.example.universityapp.presentation.navigation

import androidx.navigation.NavController
import com.example.universityapp.data.local.University

class NavigationHandler(private var navController: NavController? = null) {

    fun setNavController(controller: NavController) {
        navController = controller
    }

    fun navigateToWebView(university: University) {
        navController?.currentBackStackEntry?.savedStateHandle?.set("university", university)
        navController?.navigate(Destination.WebViewScreen.route)
    }

    fun navigateToFavorite(route: String) {
        navController?.navigate(route) {
            navController?.graph?.startDestinationRoute?.let { homeScreen ->
                popUpTo(homeScreen) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }
    }
}
object NavigationManager {
    var navController: NavController? = null

    fun navigateToWebView(university: University) {
        navController?.currentBackStackEntry?.savedStateHandle?.apply {
            set("universityUrl", university.website)
            set("universityName", university.name)
        }
        navController?.navigate(Destination.WebViewScreen.route)
    }
}