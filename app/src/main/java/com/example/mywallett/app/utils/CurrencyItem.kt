package com.example.mywallett.app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.example.a_common.data.CurrencyData
import com.example.mywallett.R

@Composable
fun CurrencyItem(
    itemHeight: Dp,
    density: Density,
    item: CurrencyData,
    offsetPopUp: Offset,
    popUpState: MutableState<Boolean>,
    onMenuMoreClicked: (Offset) -> Unit,
) {
    var itemHeight1 = itemHeight
    var offsetPopUp1 = offsetPopUp
    Card(
        Modifier
            .padding(top = horizontalPadding_16)
            .fillMaxWidth()
            .onSizeChanged {
                itemHeight1 = with(density) { it.height.toDp() }
            },
        shape = RoundedCornerShape(primaryCornerRadius_12)
    ) {
        Row(
            Modifier
                .padding(padding_10)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "1$ = ${item.rate}",
                    style = MaterialTheme.typography.subtitle1
                )

            }
            IconButton(modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                val rect = layoutCoordinates.boundsInRoot()
                offsetPopUp1 = rect.bottomRight
            }, onClick = {
                popUpState.value = true
                onMenuMoreClicked(offsetPopUp1)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.popupicon),
                    contentDescription = ""
                )
            }
        }
    }
}