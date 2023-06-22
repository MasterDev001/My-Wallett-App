package com.example.mywallett.app.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a_common.data.PersonData
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray
import com.example.presenter.persons.PersonViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun PersonItem(
    person: PersonData,
    viewModel: PersonViewModel,
    offsetPopUp: Offset,
    onItemClick: () -> Unit,
    onMenuMoreClicked: (Offset, Boolean) -> Unit
) {
    var offsetPopUp1 = offsetPopUp
    val personCurrencyList by viewModel.getPersonCurriesByPersonId(person.id)
        .collectAsState(initial = emptyList())

    Card(
        Modifier
            .padding(top = horizontalPadding_16)
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
            .onSizeChanged {
//                itemHeight1 = with(density) { it.height.toDp() }
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
                    modifier = Modifier.padding(horizontal = padding_10),
                    text = person.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )
                if (personCurrencyList.isNotEmpty()) {
                    FlowRow(
                        mainAxisSpacing = padding_10,
                        crossAxisSpacing = padding_10,
                        modifier = Modifier.padding(top = padding_10)
                    ) {
                        for (personCurrency in personCurrencyList) {
                            val currencyName = viewModel.getCurrency(personCurrency.currencyId)
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
            }
            IconButton(modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                val rect = layoutCoordinates.boundsInRoot()
                offsetPopUp1 = rect.bottomRight
            }, onClick = {
                onMenuMoreClicked(offsetPopUp1, true)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.popupicon),
                    contentDescription = ""
                )
            }
        }
    }
}