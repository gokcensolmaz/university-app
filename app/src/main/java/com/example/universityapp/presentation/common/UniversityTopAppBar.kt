package com.example.universityapp.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityTopAppBar(title: String, navigateToFavorite: () -> Unit, navigateUp: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (title != "Üniversiteler") {
                IconButton(
                    onClick = navigateUp
                ) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                }
            }
        },
        actions = {
            if (title == "Üniversiteler") {
                IconButton(
                    onClick = navigateToFavorite
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPrev() {
    // UniversityTopAppBar(title = "Üniversiteler")
}