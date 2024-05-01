package com.example.universityapp.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.universityapp.util.Constants.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityTopAppBar(title: String, navigateToFavorite: () -> Unit, navigateUp: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(SmallPadding),
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray
            )
        },
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