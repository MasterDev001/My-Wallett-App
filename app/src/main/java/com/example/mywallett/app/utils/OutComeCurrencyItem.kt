package com.example.mywallett.app.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.a_common.data.WalletOwnerData

@Composable
fun OutComeCurrencyItem(
    walletDataList: WalletOwnerData,
    currencyName: String,
    onItemClick: () -> Unit
) {

    Card(
        Modifier
            .padding(top = horizontalPadding_16)
            .fillMaxWidth()
            .clickable {
                onItemClick()
            },
        shape = RoundedCornerShape(primaryCornerRadius_12)
    ) {
        Row(
            Modifier
                .padding(padding_10)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = walletDataList.currencyBalance.toString(),
                style = MaterialTheme.typography.h5
            )
            Text(
                text = " $currencyName",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
        }
    }

}