package com.mikekorel.mobilebankingdemo.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BackButton(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Back button",
        tint = Color.Black,
        modifier = modifier
    )
}