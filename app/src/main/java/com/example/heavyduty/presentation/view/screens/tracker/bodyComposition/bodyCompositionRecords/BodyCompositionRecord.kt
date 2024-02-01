package com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.bodyCompositionRecords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyCompositionTitle.list
import com.example.heavyduty.presentation.view.theme.CardInnerContentBackGround
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar

@Composable
fun BodyCompositionRecord(){
    Column(
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(filterClickable = {})

        LazyColumn(modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxHeight(1f)
        ){
            items(6){
                BodyCompositionRecordCard()
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }
        }
    }
}

@Composable
private fun BodyCompositionRecordCard(){
    Column(modifier = Modifier
        .background(
            color = CardInnerContentBackGround,
            shape = RoundedCornerShape(20.dp)
        )
        .height(180.dp)
        .width(340.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        HeaderRow()
        Spacer(modifier = Modifier.padding(bottom = 2.dp))
        ComponentRow()
        Spacer(modifier = Modifier.padding(bottom = 2.dp))
        LazyRow{
            items(4){
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = when (it) {
                                0 -> RoundedCornerShape(bottomStart = 20.dp)
                                3 -> RoundedCornerShape(bottomEnd = 20.dp)
                                else -> RoundedCornerShape(size = 0.dp)
                            }

                        )
                        .fillMaxHeight()
                        .width(85.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Suwi",
                        color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    }
}

@Composable
private fun ComponentRow(){
    LazyRow(
        modifier = Modifier
            .width(340.dp)
            .height(60.dp)
            .background(
                color = CardInnerContentBackGround,
                shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
            )

    ){
        itemsIndexed(list)
        { index, title ->
            CustomBox(title = title)
        }
    }
}
@Composable
private fun HeaderRow(){
    Row( modifier = Modifier
        .width(340.dp)
        .height(40.dp)
        .background(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start)
    {
        Text(
            text = "suwi",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 15.dp))
    }

}

@Composable
private fun CustomBox(
    title: Int,
){
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .width(85.dp)
            .fillMaxHeight(1f),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = stringResource(id = title),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun BodyCompositionRecordPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        BodyCompositionRecord()
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyCompositionRecordCardPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        BodyCompositionRecordCard()
    }

}


