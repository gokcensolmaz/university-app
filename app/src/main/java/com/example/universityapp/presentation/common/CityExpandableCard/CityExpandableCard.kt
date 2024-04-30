package com.example.universityapp.presentation.common.CityExpandableCard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityapp.domain.model.City
import com.example.universityapp.presentation.common.UniversityCard.UniversityExpandableCard
import com.example.universityapp.ui.theme.Shapes
import com.example.universityapp.ui.theme.Typography
import com.example.universityapp.util.Constants.EXPANSION_ANIMATION_DURATION
import com.example.universityapp.util.Constants.MediumPadding
import com.example.universityapp.util.Constants.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityExpandableCard(
    city: City,
    viewModel: CityCardViewModel = hiltViewModel()
) {
    val expandedState by rememberUpdatedState(newValue = viewModel.isCityExpanded(city.id))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = EXPANSION_ANIMATION_DURATION,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        onClick = {
            if (city.universities.isNotEmpty()) {
                viewModel.toggleCityExpanded(city.id)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 45.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (city.universities.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            if (city.universities.isNotEmpty()) {
                                viewModel.toggleCityExpanded(city.id)
                            }
                        }
                    ) {
                        if (!expandedState) {
                            Icon(
                                imageVector =
                                Icons.Default.Add, contentDescription = "Expand Row"
                            )
                        } else {
                            Icon(
                                imageVector =
                                Icons.Default.Remove, contentDescription = "UnExpand Row"
                            )
                        }
                    }
                    Text(
                        text = city.province,
                        style = Typography.labelSmall
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(start = MediumPadding),
                        text = city.province,
                        style = Typography.labelSmall
                    )
                }


            }
            if (expandedState) {
                city.universities.forEach { university ->
                    UniversityExpandableCard(
                        university = university
                    )
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun CityExpandableRowPreview() {
    CityExpandableCard(
        city = City(
            id = 1,
            province = "ADANA",
            universities = listOf(
                University(
                    name = "ADANA BİLİM VE TEKNOLOJİ ÜNİVERSİTESİ",
                    phone = "0 (322) 455 00 01",
                    fax = "0 (322) 455 00 02",
                    website = "https://www.adanabtu.edu.tr",
                    email = "rektorluk@adanabtu.edu.tr",
                    adress = "Gültepe Mahallesi, Çatalan Caddesi No:201/5 01250 Sarıçam/ADANA",
                    rector = "MEHMET TÜMAY"
                )
            )
        )
    )
}*/