package com.example.mywallett.app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray
import com.example.mywallett.ui.theme.ColorRedButton

@Composable
fun PopUpToDialog(
    text: String,
    offset: Offset,
    onDismissRequest: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    itemHeight: Dp
) {
    val popUpWidth = 210.dp
    val pxValue = LocalDensity.current.run { popUpWidth.toPx() }

    Popup(
        offset = IntOffset((offset.x - pxValue).toInt(), offset.y.toInt()),
        onDismissRequest = onDismissRequest,
        properties = PopupProperties()
    ) {
        Column(
            Modifier
                .padding(5.dp)
                .background(MaterialTheme.colors.background, RoundedCornerShape(padding_10))
                .size(popUpWidth, 210.dp)
                .border(
                    2.dp,
                    color = ColorBorderGray,
                    RoundedCornerShape(10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = text,
                modifier = Modifier
                    .padding(top = 25.dp),
                fontSize = textSize_16sp
            )
            Spacer(
                modifier = Modifier
                    .padding(padding_10)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Gray)
            )
            Row(
                Modifier
                    .padding(padding_10)
                    .fillMaxWidth()
                    .clickable {
                        onEditClick()
                    }, horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(painter = painterResource(id = R.drawable.edit), contentDescription = "edit")
                Text(text = stringResource(R.string.tahrirlash), fontSize = textSize_16sp)
            }
            Row(
                Modifier
                    .padding(padding_10)
                    .fillMaxWidth()
                    .clickable {
                        onDeleteClick()
                    }, horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "edit"
                )
                Text(
                    text = stringResource(R.string.o_chirish),
                    color = ColorRedButton,
                    fontSize = textSize_16sp
                )
            }
            IconButton(
                onClick = {
                    onDismissRequest()
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "cancel"
                )
            }

        }
    }

//    DropdownMenu(
//        state,
//        onDismissRequest = onDismissRequest,
//        offset= IntOffset((offset.x).toInt(),offset.y.toInt()) as DpOffset,
//    ) {
//      DropdownMenuItem(onClick = {}) {
//          Text(text = "asdjkgj")
//      }
//
//    }
}