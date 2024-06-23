package com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.bodyCompositionRecords

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heavyduty.R
import com.example.heavyduty.data.local.Constants.CANCEL
import com.example.heavyduty.data.local.Constants.CONFIRM
import com.example.heavyduty.data.local.Constants.PROMPT
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyCompositionTitle.list
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import com.example.heavyduty.presentation.view.theme.CardInnerContentBackGround
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.MainHIT
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.view.theme.dimens
import com.example.heavyduty.presentation.view.theme.onScreenBackgroundColor
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords.BodyCompositionRecordsEvents
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords.BodyCompositionRecordsUIState
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords.BodyCompositionRecordsViewModel
import com.example.heavyduty.units.BodyCompositionComponents

@Composable
fun BodyCompositionRecord(
    bodyCompositionRecordsViewModel: BodyCompositionRecordsViewModel,
    bodyCompositionRecordsEvents: (BodyCompositionRecordsEvents) -> Unit,
    bodyCompositionRecordsUIState: BodyCompositionRecordsUIState
){
    // Prompt
    if (bodyCompositionRecordsUIState.recordDelete){
        Prompt(
            titleText = "Delete "+ stringResource(id = bodyCompositionRecordsUIState.deleteTitleText),
            message = "Do you want to delete this record ?",
            onCancel = {
              bodyCompositionRecordsEvents(BodyCompositionRecordsEvents.RecordDeleteClicked(CANCEL))
            },
            onConfirm = {
                bodyCompositionRecordsEvents(BodyCompositionRecordsEvents.RecordDeleteClicked(CONFIRM))
            }
        )

    }

    Column(
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
            .background(color = ScreenBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(filterClickable = {})

        if(bodyCompositionRecordsUIState.recordList.isEmpty()){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                Text(
                    text = "Empty Records",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        else {
            LazyColumn(modifier = Modifier
                .fillMaxHeight(1f)
            ){
                itemsIndexed(items = bodyCompositionRecordsUIState.recordList)
                {
                    index, it ->
                    val recordNum = index.plus(1).toString()

                    Spacer(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
                    if(it is Weight){
                        BodyCompositionRecordCard(
                            bodyCompositionRecordsEvents = bodyCompositionRecordsEvents,
                            headerTxt = BodyCompositionComponents.WEIGHT.component,
                            item = it,
                            recordNum = recordNum,
                            value = it.mass.toString(),
                            unit = it.unit,
                            date = it.date,
                            time = it.time
                        )
                    }
                    if(it is Height){
                        BodyCompositionRecordCard(
                            bodyCompositionRecordsEvents = bodyCompositionRecordsEvents,
                            headerTxt = BodyCompositionComponents.HEIGHT.component,
                            item = it,
                            recordNum = recordNum,
                            value = it.height.toString(),
                            unit = it.unit,
                            date = it.date,
                            time = it.time
                        )
                    }
                    if(it is BodyFat){
                        BodyCompositionRecordCard(
                            bodyCompositionRecordsEvents = bodyCompositionRecordsEvents,
                            headerTxt = BodyCompositionComponents.BODYFAT.component,
                            item = it,
                            recordNum = recordNum,
                            value = it.bodyFat.toString(),
                            unit = it.unit,
                            date = it.date,
                            time = it.time
                        )
                    }
                    if(it is MuscleMass){
                        BodyCompositionRecordCard(
                            bodyCompositionRecordsEvents = bodyCompositionRecordsEvents,
                            headerTxt = BodyCompositionComponents.MUSCLEMASS.component,
                            item = it,
                            recordNum = recordNum,
                            value = it.muscleMass.toString(),
                            unit = it.unit,
                            date = it.date,
                            time = it.time
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
                }
            }
        }
    }
}

@Composable
private fun BodyCompositionRecordCard(
    bodyCompositionRecordsEvents: (BodyCompositionRecordsEvents) -> Unit,
    headerTxt: Int = 0,
    item: Any? = null,
    recordNum: String  = "1",
    unit: String = "",
    value: String = "12",
    date: String = "dd/mm/yy",
    time: String = "hh:mm"
){
    Column(modifier = Modifier
        .background(
            color = CardInnerContentBackGround,
            shape = RoundedCornerShape(20.dp)
        )
        .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
        .height(180.dp)
        .width(340.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        HeaderRow(
            item = item,
            bodyCompositionRecordsEvents = bodyCompositionRecordsEvents,
            headerTxt = headerTxt)
        Spacer(modifier = Modifier.padding(bottom = 2.dp))
        ComponentRow(unit = unit)
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
                        text =
                        when(it){
                            0 -> recordNum
                            1 -> value
                            2 -> date
                            3 -> time
                            else -> ""
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

@Composable
private fun ComponentRow(unit: String)
{
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
            CustomBox(
                title = title,
                unit = unit )
        }
    }
}
@Composable
private fun HeaderRow(
    item: Any? = null,
    bodyCompositionRecordsEvents: (BodyCompositionRecordsEvents) -> Unit,
    headerTxt: Int = 0,
){
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = headerTxt),
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 15.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        bodyCompositionRecordsEvents(
                            BodyCompositionRecordsEvents.RecordDeleteClicked(
                                PROMPT,
                                item
                            )
                        )
                    },
                painter = painterResource(id = R.drawable.delete_icn),
                tint = Color.White,
                contentDescription = null)
        }

    }

}

@Composable
private fun CustomBox(
    title: Int,
    unit: String,
){
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .width(85.dp)
            .fillMaxHeight(1f),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = if(title == R.string.value) { stringResource(id = title) + "\n${unit}" } else {stringResource(id = title)},
            color = Color.White,
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
        BodyCompositionRecord(
            bodyCompositionRecordsViewModel = hiltViewModel(),
            bodyCompositionRecordsEvents = {},
            bodyCompositionRecordsUIState = BodyCompositionRecordsUIState()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyCompositionRecordCardPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        BodyCompositionRecordCard(
            bodyCompositionRecordsEvents = {}
        )
    }

}


