package com.example.mywallett.app.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.mywallett.R
import com.example.presenter.lend.LendViewModel

@Composable
fun LendWalletDropDown(
    modifier: Modifier = Modifier,
    viewModel: LendViewModel,
    selectedCurrency: CurrencyData,
    list: List<WalletData>,
    selectedWallet: (WalletData) -> Unit,
    selectedWalletOwner: (WalletOwnerData?) -> Unit,
) {

    var dropDownState by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(list[0]) }
    var newWalletOwner by remember { mutableStateOf<WalletOwnerData?>(null) }

    if (viewModel.isCurrencyIdExistsInWallet(selectedItem.id, selectedCurrency.id)) {
        selectedItem.walletOwnerDataList.walletOwnerData.forEach {
            if (it.currencyId == selectedCurrency.id) {
                newWalletOwner = it
            }
        }
    } else {
        newWalletOwner = null
    }

    Row(
        modifier
            .clickable { dropDownState = !dropDownState }
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = horizontalPadding_16, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(text = selectedItem.name)
            Text(
                modifier = Modifier.padding(5.dp),
                text = "${newWalletOwner?.currencyBalance ?: 0.0} ${selectedCurrency.name}",
                fontSize = 12.sp
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.more),
            contentDescription = "more"
        )
        DropdownMenu(
            expanded = dropDownState,
            modifier = Modifier.heightIn(min = 0.dp, max = 200.dp),
            onDismissRequest = { dropDownState = false }) {
            for (item in list) {
                DropdownMenuItem(onClick = {
                    selectedItem = item
                    dropDownState = false
                }) {
                    Text(text = item.name)
                }
            }
        }
    }
    selectedWallet(selectedItem)
    selectedWalletOwner(newWalletOwner)
}