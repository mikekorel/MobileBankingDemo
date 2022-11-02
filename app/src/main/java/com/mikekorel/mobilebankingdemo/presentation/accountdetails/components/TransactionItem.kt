package com.mikekorel.mobilebankingdemo.presentation.accountdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikekorel.mobilebankingdemo.core.capitalizeFirstChar
import com.mikekorel.mobilebankingdemo.domain.model.Transaction
import com.mikekorel.mobilebankingdemo.presentation.ui.theme.CardColorLightGray

@Preview
@Composable
fun TransactionItemPreview() {
    TransactionItem(
        transaction = Transaction(
            id = "d1e904fa-8ae6-49ee-ab5a-f4e7947a74ab",
            date = "2016-06-05T10:15:30Z",
            transactionAmount = "32.00",
            transactionType = "transfer own",
            description = "transfer between my accountstransfer between my accountstransfer between my accountstransfer between my accounts",
            isDebit = true
        ),
        "EUR"
    )
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    currencyCode: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(bottom = 6.dp)
            .background(CardColorLightGray)
            .border(width = 1.dp, color = Color.DarkGray, shape = RectangleShape)
    ) {
        Text(
            text = transaction.transactionType?.capitalizeFirstChar() ?: "",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        Text(
            text = transaction.transactionAmount?.plus(" $currencyCode") ?: "",
            fontWeight = FontWeight.Bold,
            color = if (transaction.isDebit == true) Color.Red else Color.Green,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        Text(
            text = transaction.description ?: "",
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis ,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }

}