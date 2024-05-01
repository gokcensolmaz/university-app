package com.example.universityapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.universityapp.R

@Composable
fun ErrorScreen() {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1500),
        label = ""

    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp).alpha(alphaAnimation),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Gray
        )
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "Bir hatayla karşılaşıldı!",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray

        )
    }
}