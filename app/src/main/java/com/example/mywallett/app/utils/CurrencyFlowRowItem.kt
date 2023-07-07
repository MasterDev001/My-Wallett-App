package com.example.mywallett.app.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonCurrencyData
import com.example.mywallett.ui.theme.ColorBorderGray
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CurrencyFlowRowItem(
    personCurrencyList: List<PersonCurrencyData>,
    getCurrency: (String) -> CurrencyData,
) {
    FlowRow(
        mainAxisSpacing = padding_10,
        crossAxisSpacing = padding_10,
        modifier = Modifier.padding(top = padding_10)
    ) {
        for (personCurrency in personCurrencyList) {
            val currencyName = getCurrency(personCurrency.currencyId)
            Surface(
                modifier = Modifier
                    .background(Color.Unspecified),
                shape = RoundedCornerShape(cornerRadius_8),
                border = BorderStroke(width = 1.dp, color = ColorBorderGray)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    fontSize = 14.sp,
                    text = "${personCurrency.currencyBalance} ${currencyName.name}"
                )
            }
        }
    }
}