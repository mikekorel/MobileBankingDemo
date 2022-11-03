package com.mikekorel.mobilebankingdemo.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    tint: Color = Color.LightGray
) {
    Icon(
        imageVector = Icons.Default.Star,
        contentDescription = "Favorite button",
        tint = tint,
        modifier = modifier
    )
}