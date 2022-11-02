package com.mikekorel.mobilebankingdemo.presentation.accountdetails.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AccountDetailsItem (
    labelText: String,
    valueText: String?,
    modifier: Modifier = Modifier
) {
    val styledText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(labelText)
        }
        append(valueText ?: "")
    }
    Text(
        text = styledText,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(4.dp))
}