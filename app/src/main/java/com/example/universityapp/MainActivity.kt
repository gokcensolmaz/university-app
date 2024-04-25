package com.example.universityapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.universityapp.presentation.common.UniversityTopAppBar
import com.example.universityapp.presentation.home.HomeScreen
import com.example.universityapp.presentation.home.HomeViewModel
import com.example.universityapp.presentation.navigation.Destination
import com.example.universityapp.presentation.navigation.Navigation
import com.example.universityapp.ui.theme.UniversityappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversityappTheme {
                var topAppBarTitle by remember { mutableStateOf("Üniversiteler") }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { UniversityTopAppBar(title = topAppBarTitle) },

                ){
                    val topPadding = it.calculateTopPadding()
                    Navigation(modifier = Modifier.padding(top = topPadding))
                }
            }
        }
    }
}
