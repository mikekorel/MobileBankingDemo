package com.mikekorel.mobilebankingdemo.presentation.accountslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikekorel.mobilebankingdemo.R
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.ui.theme.CardColorLightGray

@Preview
@Composable
fun AccountsListItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AccountsListItem(
            account = AccountsListItem(
                id = "1f34c76a-b3d1-43bc-af91-a82716f1bc2e",
                accountNumber = 12345,
                balance = "99.00",
                currencyCode = "EUR",
                accountType = "current",
                accountNickname = "My Salary"
            ),
            onItemClick = {}
        )
    }
}

@Composable
fun AccountsListItem (
    account: AccountsListItem,
    onItemClick: (AccountsListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(CardColorLightGray)
            .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(14.dp))
            .clickable { onItemClick(account) }
    ) {
        Text(
            text = account.accountNickname ?: account.accountNumber?.toString() ?: stringResource(R.string.account),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        )
        Text(
            text = account.accountType ?: "",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        )
        account.balance?.let { balance ->
            Text(
                text = balance.plus((" ${account.currencyCode ?: ""}")),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 42.dp)
            )
        }

    }
}