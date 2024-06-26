package com.example.universityapp.presentation.common.UniversityCard

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityapp.data.local.University
import com.example.universityapp.domain.model.ContactInfo
import com.example.universityapp.presentation.navigation.NavigationHandler
import com.example.universityapp.presentation.navigation.NavigationManager
import com.example.universityapp.ui.theme.Shapes
import com.example.universityapp.ui.theme.Typography
import com.example.universityapp.util.Constants
import com.example.universityapp.util.Constants.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityExpandableCard(
    university: University,
    viewModel: UniversityViewModel = hiltViewModel()
) {
    val expandedState by rememberUpdatedState(newValue = viewModel.isUniversityExpanded(university.name))
    val hasInformation = hasInformation(university)
    val favoriteState by rememberUpdatedState(newValue = viewModel.isUniversityFavorite(university))


    Box(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                durationMillis = Constants.EXPANSION_ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
        .clip(Shapes.medium).background(Color.LightGray)
        .clickable {
            if (hasInformation) {
                viewModel.toggleUniversityExpanded(university.name)
            }
        }) {
        Column(
            modifier = Modifier.padding(SmallPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (hasInformation) {
                        IconButton(onClick = {
                            viewModel.toggleUniversityExpanded(university.name)
                        }) {
                            if (!expandedState) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Expand Row"
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "UnExpand Row"
                                )
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.width(48.dp))
                    }
                    Text(
                        text = university.name, style = Typography.labelSmall
                    )


                }
                IconButton(onClick = {
                    viewModel.onEvent(AddRemoveFavoriteEvent.UpsertDeleteUniversity(university))
                }) {
                    if (favoriteState) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    } else {
                        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
                    }
                }
            }
            if (expandedState) {
                val context = LocalContext.current

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 48.dp)
                        .clip(Shapes.medium)
                        .background(Color.LightGray),

                    ) {

                    Column(modifier = Modifier.padding(SmallPadding)) {
                        var contactInfoList = getUniversityInformation(university)

                        contactInfoList.forEach { element ->
                            if (element.value != "-") {
                                when (element.label) {
                                    "Phone" -> Row() {
                                        HyperlinkText(
                                            text = "${element.label}: ${element.value}",
                                            hyperlinkText = element.value,
                                            onClick = {
                                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                                    data =
                                                        Uri.parse("tel:${element.value}")
                                                }
                                                context.startActivity(intent)
                                            }
                                        )
                                    }

                                    "Website" -> Row() {
                                        HyperlinkText(
                                            text = "${element.label}: ${element.value}",
                                            hyperlinkText = element.value,
                                            onClick = {
                                                NavigationManager.navigateToWebView(university)
                                            }
                                        )
                                    }

                                    "Email" -> Row() {
                                        HyperlinkText(
                                            text = "${element.label}: ${element.value}",
                                            hyperlinkText = element.value,
                                            onClick = {
                                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                                    data =
                                                        Uri.parse("mailto:${element.value}")
                                                }
                                                context.startActivity(intent)
                                            }
                                        )
                                    }

                                    else -> Row() {
                                        Text("${element.label}: ${element.value}")
                                    }
                                }
                                if (element.label != "Rector") {
                                    Divider(
                                        color = Color.Gray,
                                        thickness = 1.dp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                            .padding(vertical = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}


@Composable
fun HyperlinkText(text: String, hyperlinkText: String, onClick: () -> Unit) {
    val annotatedLinkString = buildAnnotatedString {
        val str = text
        val startIndex = str.indexOf(hyperlinkText)
        val endIndex = startIndex + hyperlinkText.length
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff000080),
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex
        )
    }

    Text(
        modifier = Modifier.clickable { onClick() },
        text = annotatedLinkString
    )

}

fun hasInformation(university: University): Boolean {
    return listOf(
        university.phone,
        university.fax,
        university.website,
        university.email,
        university.adress,
        university.rector
    ).any { it != "-" }
}

fun getUniversityInformation(university: University): List<ContactInfo> {
    return listOf(
        ContactInfo("Phone", university.phone),
        ContactInfo("Fax", university.fax),
        ContactInfo("Website", university.website),
        ContactInfo("Email", university.email),
        ContactInfo("Address", university.adress),
        ContactInfo("Rector", university.rector)
    )
}

/*@Preview(showBackground = true)
@Composable
fun uniCardPrev() {
    UniversityExpandableCard(
        university = University(
            name = "ADANA BİLİM VE TEKNOLOJİ ÜNİVERSİTESİ",
            phone = "0 (322) 455 00 01",
            fax = "0 (322) 455 00 02",
            website = "https://www.adanabtu.edu.tr",
            email = "rektorluk@adanabtu.edu.tr",
            adress = "Gültepe Mahallesi, Çatalan Caddesi No:201/5 01250 Sarıçam/ADANA",
            rector = "MEHMET TÜMAY"
        )
    ){}
}*/