package com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.bodyCompositionRecords

import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyCompositionTitle.list
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import com.example.heavyduty.presentation.view.theme.CardInnerContentBackGround
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords.BodyCompositionRecordsUIState
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords.BodyCompositionRecordsViewModel
import com.example.heavyduty.units.BodyCompositionComponents
import java.time.format.DateTimeFormatter

@Composable
fun BodyCompositionRecord(
   bodyCompositionRecordsUIState: BodyCompositionRecordsUIState
){
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d-MMMM-yy")

    Log.i("records", bodyCompositionRecordsUIState.recordList.toString())
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
            .fillMaxHeight(1f)
        ){

            items(bodyCompositionRecordsUIState.recordList){

                Spacer(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

                if(it is Weight){
                    BodyCompositionRecordCard(
                        headerTxt = BodyCompositionComponents.WEIGHT.component,
                        recordNum = it.id.toString(),
                        value = it.mass.toString(),
                        unit = it.unit,
                        date = it.date,
                        time = it.time
                    )
                }

                if(it is Height){
                    BodyCompositionRecordCard(
                        headerTxt = BodyCompositionComponents.HEIGHT.component,
                        recordNum = it.id.toString(),
                        value = it.height.toString(),
                        unit = it.unit,
                        date = it.date,
                        time = it.time
                    )
                }

                if(it is BodyFat){
                    BodyCompositionRecordCard(
                        headerTxt = BodyCompositionComponents.BODYFAT.component,
                        recordNum = it.id.toString(),
                        value = it.bodyFat.toString(),
                        unit = it.unit,
                        date = it.date,
                        time = it.time
                    )
                }

                if(it is MuscleMass){
                    BodyCompositionRecordCard(
                        headerTxt = BodyCompositionComponents.MUSCLEMASS.component,
                        recordNum = it.id.toString(),
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

@Composable
private fun BodyCompositionRecordCard(
    headerTxt: Int = 0,
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
        HeaderRow(headerTxt = headerTxt)
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
                        color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    }
}

@Composable
private fun ComponentRow(
    unit: String
){
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
        Text(
            text = stringResource(id = headerTxt),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 15.dp))
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
        BodyCompositionRecord(
           bodyCompositionRecordsUIState = BodyCompositionRecordsUIState()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyCompositionRecordCardPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        BodyCompositionRecordCard()
    }

}


