package com.example.universityapp.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.UnfoldLess
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityapp.presentation.common.CityExpandableCard.CityCardViewModel
import com.example.universityapp.util.Constants.SmallPadding

@Composable
fun CollapseAllButton(viewModel: CityCardViewModel = hiltViewModel()) {
    FloatingActionButton(
        modifier = Modifier.padding(SmallPadding),
        onClick = { viewModel.collapseAllCities()},
        containerColor = Color.DarkGray
    ) {
        Icon(
            modifier = Modifier.size(35.dp),
            imageVector = Icons.Default.UnfoldLess,
            contentDescription = "Floating action button.",
            tint = Color.LightGray
        )
    }
}