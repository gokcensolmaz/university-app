package com.example.universityapp.presentation.common

import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.universityapp.domain.model.University
import com.example.universityapp.ui.theme.Shapes
import com.example.universityapp.ui.theme.Typography
import com.example.universityapp.util.Constants
import com.example.universityapp.util.Constants.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityExpandableCard(
    university: University,
    expanded: Boolean
) {
    var expandedState by remember { mutableStateOf(expanded) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = Constants.EXPANSION_ANIMATION_DURATION,
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
                .padding(Constants.SmallPadding)
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
                    text = university.name,
                    style = Typography.labelSmall
                )


            }
            if (expandedState) {
                val context = LocalContext.current

                Card() {

                    Column(modifier = Modifier.padding(SmallPadding)) {
                        Text(
                            text = "Phone: ${university.phone}",
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = android.net.Uri.parse("tel:${university.phone}")
                                }
                                context.startActivity(intent)
                            }
                        )
                        Text("fax: ${university.fax}")
                        Text(
                            text = "website: ${university.website}",
                            textDecoration = TextDecoration.Underline
                        )
                        Text("email: ${university.email}")
                        Text("address: ${university.adress}")
                        Text("rector: ${university.rector}")
                    }
                }

            }
        }

    }
}

@Preview(showBackground = true)
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
        ),
        expanded = false
    )
}