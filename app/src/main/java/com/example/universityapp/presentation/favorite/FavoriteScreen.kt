package com.example.universityapp.presentation.favorite

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.universityapp.presentation.common.UniversityCard.FavoriteState
import com.example.universityapp.presentation.common.UniversityCard.UniversityExpandableCard
import com.example.universityapp.util.Constants
import com.example.universityapp.util.Constants.SmallPadding

@Composable
fun FavoriteScreen(
    state: FavoriteState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.SmallPadding)
    ) {
        items(state.universities) { university ->
            UniversityExpandableCard(
                university = university
            )
            Spacer(modifier = Modifier.height(SmallPadding))
        }
    }

}