package com.example.mywallett.app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a_common.Type
import com.example.a_common.data.HistoryData
import com.example.a_common.getTypeEnum
import com.example.a_common.getTypeNumber
import com.example.a_common.getTypeText
import com.example.a_common.huminize
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray
import com.example.mywallett.ui.theme.ColorGreenText
import com.example.mywallett.ui.theme.ColorRedButton

@Composable
fun HistoryItem(
    item: HistoryData,
    horizontalPadding: Dp = 10.dp,
    verticalPadding: Dp = 2.dp,
    background: Color = Color.White,
    onIconClicked: () -> Unit
) {
    var visibilityComment by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding, horizontal = horizontalPadding)
            .clip(RoundedCornerShape(6.dp))
            .background(color = background)
            .border(
                width = 1.dp,
                color = ColorBorderGray,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {
                visibilityComment = !visibilityComment
            }
            .padding(5.dp)
    ) {

        var incr = ""
        var color = Color.Black
        if (item.title == getTypeNumber(Type.INCOME) || item.title == getTypeNumber(Type.BORROW)) {
            incr = "+"
            color = ColorGreenText
        } else if (item.title == getTypeNumber(Type.OUTCOME) || item.title == getTypeNumber(Type.LEND)) {
            incr = "-"
            color = ColorRedButton
        }
        val idPerson = R.drawable.persons
        val idPocket = R.drawable.wallet

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = getTypeText(item.title),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (!item.uploaded) {
                Image(
                    modifier = Modifier.clickable { onIconClicked() },
                    painter = painterResource(id = R.drawable.error), contentDescription = ""
                )
            }
            Text(
                text = "$incr${item.amount.huminize()} ${item.currency}",
                color = color,
                fontWeight = FontWeight.Bold
            )
        }
        if (!item.fromName.isNullOrEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = if (item.isFromPocket) idPocket else idPerson),
                    contentDescription = "person",
                )
                val pocket = if (item.isFromPocket) " hamyon" else ""
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp),
                    text = (item.fromName ?: "") + "${pocket}dan",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (getTypeEnum(item.title) == Type.CONVERTATION) {
                    Text(
                        text = "-${item.moneyFrom?.huminize()} ${item.moneyNameFrom}",
                        color = ColorRedButton,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }

            }
        }
        if (!item.toName.isNullOrEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = if (item.isToPocket) idPocket else idPerson),
                    contentDescription = "person",
                )
                val pocket = if (item.isToPocket) " hamyon" else ""
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp),
                    text = (item.toName ?: "") + "${pocket}ga",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (getTypeEnum(item.title) == Type.CONVERTATION) {
                    Text(
                        text = "+${item.moneyTo?.huminize()} ${item.moneyNameTo}",
                        color = ColorGreenText,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

//            val kurs =
//                if (item.rateFrom > item.rateTo) {
//                    "1 ${item.moneyNameTo} = ${(item.rateFrom / item.rateTo).huminize()} ${item.moneyNameFrom}"
//                } else if (item.rateFrom < item.rateTo) {
//                    "1 ${item.moneyNameFrom} = ${(item.rateTo / item.rateFrom).huminize()} ${item.moneyNameTo}"
//                } else if (item.rateFrom != 1.0 && item.rateTo != 1.0) {
//                    "1$ = ${item.rateTo.huminize()}"
//                } else " "
            val value = if (!item.fromName.isNullOrEmpty()) {
                "${item.rateFrom} ${item.moneyNameFrom} = ${item.rateTo} ${item.moneyNameTo}"
            } else {
                "1$ = ${item.rateTo}"
            }
            Text(
                text = value,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.date.huminize(),
                fontSize = 12.sp,
            )
        }


        if (visibilityComment) {
            var izoh = ""
            item.comment?.let {
                if (it.isNotEmpty()) {
                    izoh = "\nizoh: $it"
                }
            }
            Text(
                text = "oldingi balans: ${item.balance.huminize()} $$izoh",
                fontSize = 12.sp
            )
        }
    }
}