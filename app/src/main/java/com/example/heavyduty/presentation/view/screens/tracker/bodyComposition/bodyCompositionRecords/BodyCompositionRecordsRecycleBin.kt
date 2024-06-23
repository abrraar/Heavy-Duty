package com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.bodyCompositionRecords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyCompositionTitle.list
import com.example.heavyduty.presentation.view.theme.NonIntractableBackgroundColor
import com.example.heavyduty.presentation.view.theme.IntractableBackgroundColor


@Composable
fun BodyCompositionRecordsRecycleBin(){
    Column(modifier = Modifier
        .background(
            color = NonIntractableBackgroundColor,
            shape = RoundedCornerShape(20.dp)
        )
        .height(100.dp)
        .width(330.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        HeaderRow()
    }
}

@Composable
private fun HeaderRow(){
        LazyRow(
            modifier = Modifier
                .width(330.dp)
                .height(50.dp)
                .background(
                    color = NonIntractableBackgroundColor,
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )

        ){
            itemsIndexed(list)
            { index, title ->
                when(index){
                    0 -> CustomBox(title = title, topRightCorner = 0 , topLeftCorner = 20)
                    3 -> CustomBox(title = title, topRightCorner = 20 , topLeftCorner = 0)
                    else -> CustomBox(title = title, topRightCorner = 0 , topLeftCorner = 0)
                }
            }
        }
}

@Composable
private fun CustomBox(
    title: Int,
    topRightCorner: Int,
    topLeftCorner: Int
){
    Box(
        modifier = Modifier
            .background(
                color = IntractableBackgroundColor,
                shape = RoundedCornerShape(topStart = topLeftCorner.dp, topEnd = topRightCorner.dp)
            )
            .height(50.dp)
            .width(82.dp),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = stringResource(id = title),
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Preview()
@Composable
private fun BodyCompositionHistoryPreview(){
    BodyCompositionRecordsRecycleBin()
}

@Preview()
@Composable
private fun HeaderRowPreview(){
    HeaderRow()
}





