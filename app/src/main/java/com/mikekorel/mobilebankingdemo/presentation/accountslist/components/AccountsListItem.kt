package com.mikekorel.mobilebankingdemo.presentation.accountslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikekorel.mobilebankingdemo.R
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem

@Composable
fun AccountsListItem (
    account: AccountsListItem,
    onItemClick: (AccountsListItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(42.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.LightGray)
            .clickable { onItemClick(account) }
    ) {
        Text(
            text = account.accountNickname ?: stringResource(R.string.account),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        )
        account.balance?.let { balance ->
            Text(
                text = balance.plus((" ${account.currencyCode ?: ""}")),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 8.dp)
            )
        }

    }
}