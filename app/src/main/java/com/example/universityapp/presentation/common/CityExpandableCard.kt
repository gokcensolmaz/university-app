package com.example.universityapp.presentation.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.universityapp.domain.model.City
import com.example.universityapp.domain.model.University
import com.example.universityapp.ui.theme.Shapes
import com.example.universityapp.ui.theme.Typography
import com.example.universityapp.util.Constants.EXPANSION_ANIMATION_DURATION
import com.example.universityapp.util.Constants.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityExpandableCard(
    modifier: Modifier = Modifier,
    city: City,
    expanded: Boolean,
    onClickExpanded: () -> Unit
) {
    var expandedState by remember { mutableStateOf(expanded) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = EXPANSION_ANIMATION_DURATION,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        expandedState = !expandedState
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


            }
            if (expandedState) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(SmallPadding),
                    contentPadding = PaddingValues(all = SmallPadding)
                ) {
                    items(count = city.universities.size) {
                        UniversityExpandableCard(
                            modifier = modifier,
                            university = city.universities[it],
                            expanded = false
                        ) {}
                    }
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun CityExpandableRowPreview() {
    CityExpandableCard(
        modifier = Modifier.fillMaxWidth(),
        city = City(
            id = 1,
            province = "ANKARA",
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
        ),
        expanded = true
    ) {}
}