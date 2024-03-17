package com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.main

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.R
import com.example.heavyduty.data.local.Constants
import com.example.heavyduty.data.local.Constants.BODY_COMP_LEFT_BTN
import com.example.heavyduty.data.local.Constants.BODY_COMP_RIGHT_BTN
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionTexts.columnTexts
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionTexts.rowTexts
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.theme.CardInnerContentBackGround
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main.BodyCompositionEvents
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main.BodyCompositionUIState
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main.BodyCompositionViewModel
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import rememberMarker
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun BodyCompositionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: BodyCompositionUIState,
    bodyCompositionEvents: (BodyCompositionEvents) -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .verticalScroll(rememberScrollState())
    ){
        Column()
        {
            Graph(bodyCompositionUIState = state)
            GraphStatus(
                bodyCompositionEvents = bodyCompositionEvents,
                bodyCompositionUIState = state,
                )
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
        ) {
            Body()
            RecordsListAndRecordEntry(
                navController = navController,
                bodyCompositionUIState = state)
        }
        
    }

}
//-------------------------- Start Of Body -----------------------------------
@Composable
private fun Body(){
    Column(
        modifier = Modifier
            .height(380.dp)
            .fillMaxWidth()
            .background(color = CardInnerContentBackGround)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(60.dp)
                .padding(bottom = 2.dp)
                .background(color = MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.measure_difference),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        LazyColumn(
            userScrollEnabled = false
        ){
            itemsIndexed(columnTexts){
                index, item ->
                Row(
                    modifier = Modifier.padding(bottom = 2.dp)
                ) {
                    // This is where the headings of the records and differences are displayed
                    Box(
                        modifier = Modifier
                            .height(
                                if (index < 2) {
                                    100.dp
                                } else {
                                    120.dp
                                }
                            )
                            .width(120.dp)
                            .padding(end = 2.dp)
                            .background(color = MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center,

                        ) {
                        Text(text = stringResource(id = item),
                            modifier = Modifier.fillMaxWidth(1f),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Center
                        )
                    }
                    // This is where the records are selected and displayed
                    Column(
                        modifier = Modifier
                            .height(
                                if (index < 2) {
                                    100.dp
                                } else {
                                    120.dp
                                }
                            )
                            .fillMaxWidth(1f)
                            .background(color = ScreenBackgroundColor)
                            .clickable(
                                onClick = {
                                    when (index) {
                                        0 -> {}
                                        1 -> {}
                                        2 -> {}
                                        else -> {}
                                    }
                                }
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = if (index<2) {Arrangement.Center} else {Arrangement.Top},

                        ) {
                        if(index == 2)
                        {
                            LazyRow(modifier = Modifier
                                .height(40.dp)
                                .fillMaxWidth(1f)
                                .background(color = MaterialTheme.colorScheme.primary)) {
                                items(rowTexts.size){
                                        index ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(135.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = stringResource(id = rowTexts[index]),
                                            style = MaterialTheme.typography.titleSmall,
                                            color = MaterialTheme.colorScheme.onPrimary)
                                        Text(
                                            text = " / "+ stringResource(id = R.string.kg),
                                            style = MaterialTheme.typography.titleSmall,
                                            color = MaterialTheme.colorScheme.onPrimary)
                                    }
                                }
                            }
                        }
                        else{
                            Text(
                                text = stringResource(id = R.string.enter_record_hint),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.alpha(0.5f)
                            )
                        }
                    }
                }
            }
        }
    }
}
//-------------------------- End Of Body -----------------------------------
@Composable
private fun Graph(
    bodyCompositionUIState: BodyCompositionUIState,
){
    val chartModel = bodyCompositionUIState.model
    val date = bodyCompositionUIState.date
    
    val lineSpec = arrayListOf<LineChart.LineSpec>()
    lineSpec.add(
        LineChart.LineSpec(
            point = shapeComponent(
                shape = Shapes.pillShape,
                color = MaterialTheme.colorScheme.primary),
            lineColor = MaterialTheme.colorScheme.primary.toArgb(),
            lineBackgroundShader = DynamicShaders.fromBrush(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        Color.Transparent
                    )
                )
            )
        )
    )

    if(chartModel == null && date == null){
        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .height(280.dp)
            .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(
                text = "Data not available",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground)
        }
    }
    else
    {
        ProvideChartStyle(
            chartStyle = ChartStyle(
                axis = ChartStyle.Axis(
                    axisLabelColor = Color.White,
                    axisLineColor = MaterialTheme.colorScheme.primary,
                    axisGuidelineColor = MaterialTheme.colorScheme.primary

                ),
                lineChart = ChartStyle.LineChart(
                    spacing = 100.dp,
                    lines = lineSpec
                ),
                elevationOverlayColor = MaterialTheme.colorScheme.primary,
                columnChart = ChartStyle.ColumnChart(columns = emptyList()),
                marker = ChartStyle.Marker()
            )
        ) {
            Chart(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = 15.dp, bottom = 15.dp, start = 15.dp)
                    .height(250.dp),
                marker = rememberMarker(),
                chart = lineChart(
                    lines = lineSpec
                ),
                chartScrollState = rememberChartScrollState() ,
                model = chartModel!!.value,
                startAxis = rememberStartAxis(
                    axis = axisLineComponent(
                        strokeColor = Color.White,
                        strokeWidth = 2.dp
                    ),
                    itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 5),
                    guideline = axisGuidelineComponent(
                        color = MaterialTheme.colorScheme.onBackground),
                    label = axisLabelComponent(
                        color = Color.White)
                ),
                bottomAxis = date?.let {
                    rememberBottomAxis(
                        guideline = null,
                        axis = axisLineComponent(
                            strokeColor = Color.White,
                            strokeWidth = 2.dp
                        ),
                        label = axisLabelComponent(
                            lineCount = 1,
                            color = Color.White),
                        valueFormatter = it.value,
                    )
                })
        }
    }
}


//--------------- Start Of Graph Navigation ------------------------


@Composable
private fun GraphStatus(
    bodyCompositionEvents: (BodyCompositionEvents) -> Unit,
    bodyCompositionUIState: BodyCompositionUIState,
){

    val context = LocalContext.current
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically)
    {
        IconButton(
            modifier = Modifier
                .weight(1f)
                .height(140.dp),
            onClick = {
                if (bodyCompositionUIState.leftButton){
                    bodyCompositionEvents(BodyCompositionEvents.DisplayGraph( BODY_COMP_LEFT_BTN ))
                }else {
                    Toast.makeText(context, "no components available", Toast.LENGTH_SHORT).show()
                }
            }) {
            Icon(
                tint = if( bodyCompositionUIState.leftButton){
                    Color.White} else {
                    Color.White.copy(0.3f)},
                painter = painterResource(id = R.drawable.double_arrow_left_icn),
                contentDescription = "")
        }
        Column(
            modifier = Modifier
                .height(140.dp)
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = stringResource(id = bodyCompositionUIState.title),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.headlineSmall
            )
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Text(
                        text = "10",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.headlineLarge)
                    Text(
                        text = "kg",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.labelSmall)
                }
                Text(
                    text = stringResource(id = R.string.date),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary)
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Row {
                Text(
                    modifier = Modifier.padding(end = 5.dp),
                    text = "total records:",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleSmall)
                Text(
                    text = bodyCompositionUIState.totalRecords.toString(),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleSmall)
            }

        }
        IconButton(
            modifier = Modifier
                .weight(1f)
                .height(140.dp),
            onClick = {
                if (bodyCompositionUIState.rightButton){
                    bodyCompositionEvents(BodyCompositionEvents.DisplayGraph(BODY_COMP_RIGHT_BTN))
                }else {
                    Toast.makeText(context, "no components available", Toast.LENGTH_SHORT).show()
                }

            }) {
            Icon(
                tint = if( bodyCompositionUIState.rightButton){
                    Color.White} else {
                    Color.White.copy(0.3f)},
                painter = painterResource(id = R.drawable.double_arrow_right_icn),
                contentDescription = "")
        }

    }
}

//---------------------- Start Of Graph Navigation ------------------------

@Composable
fun RecordsListAndRecordEntry(
    navController: NavController,
    bodyCompositionUIState: BodyCompositionUIState
){
    Row(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth(1f)
            .height(120.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomButton(
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            text = stringResource(id = R.string.enter_data_btn),
            onClick = {
                navController.navigate(NavigationScreenNames.AddBodyCompositionRecords.route)
                      },
            style = MaterialTheme.typography.titleSmall
        )
        CustomButton(
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            text = stringResource(id = R.string.records_btn),
            onClick = {
                navController.navigate(NavigationScreenNames.BodyCompositionRecords.route)
                      },
            style = MaterialTheme.typography.titleSmall
        )

    }
}

//--------------- End Of Graph Navigation ------------------------

////--------------- Start Of Graph Header ----------------------
//@Composable
//fun GraphHeader(){
//
//    Row(modifier = Modifier
//        .background(color = MaterialTheme.colorScheme.secondary)
//        .fillMaxWidth(1f)
//        .height(60.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.End
//    ) {
//        Icon(
//            modifier = Modifier.clickable {
//
//            },
//            painter = painterResource(id = R.drawable.graph_icn),
//            contentDescription = null,
//            tint = Color.White )
//        Spacer(modifier = Modifier.padding(10.dp))
//    }
//}
//
////--------------- Start Of Graph Header----------------------

@Preview
@Composable
private fun BodyPreview(){
    HeavyDutyTheme(dynamicColor = false){
        Body()
    }
}

@Preview( showBackground = true)
@Composable
private fun BodyCompositionScreenPreview(){
    HeavyDutyTheme(dynamicColor = false){
        val state = BodyCompositionUIState()
        BodyCompositionScreen(
            navController = rememberNavController(),
            bodyCompositionEvents = {},
            state = state)
    }
}

@Preview(
    showSystemUi = false,
    showBackground = false,)
@Composable
private fun GraphHeaderPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        val lineSpec = arrayListOf<LineChart.LineSpec>()
        lineSpec.add(
            LineChart.LineSpec(
                point = shapeComponent(
                    shape = Shapes.pillShape,
                    color = MaterialTheme.colorScheme.primary),
                lineColor = MaterialTheme.colorScheme.primary.toArgb(),
                lineBackgroundShader = DynamicShaders.fromBrush(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            Color.Transparent
                        )
                    )
                )
            )
        )

        val data = listOf(
            "2022-07-01" to 10f,
            "2022-07-02" to 50f,"2022-07-03" to 82f,
            "2022-07-04" to 100f,"2022-07-05" to 30.9f,
            "2022-07-06" to 80f,"2022-07-07" to 30.9f,
            "2022-07-08" to 80f,"2022-07-09" to 30.9f,
            "2022-07-10" to 80f,"2022-07-11" to 30.9f ,
            "2022-07-12" to 80f,"2022-07-13" to 30.9f  )
        val yData = data.associate{ (dateString, yValue) -> LocalDate.parse(dateString) to yValue }
        val xValuesToDates = yData.keys.associateBy { it.toEpochDay().toFloat() }
        val chartEntryModel = entryModelOf(xValuesToDates.keys.zip(other = yData.values,transform = ::entryOf))
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yy")
        val horizontalAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
            (xValuesToDates[value] ?: LocalDate.ofEpochDay(value.toLong())).format(dateTimeFormatter)
        }

        val chartStyle = ChartStyle(
            axis = ChartStyle.Axis(
                axisLabelColor = Color.White,
                axisLineColor = MaterialTheme.colorScheme.primary,
                axisGuidelineColor = MaterialTheme.colorScheme.primary

            ),
            lineChart = ChartStyle.LineChart(
                spacing = 100.dp,
                lines = lineSpec
            ),
            elevationOverlayColor = MaterialTheme.colorScheme.primary,
            columnChart = ChartStyle.ColumnChart(columns = emptyList()),
            marker = ChartStyle.Marker()

        )

        ProvideChartStyle(
            chartStyle = chartStyle
        ) {
            Chart(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = 15.dp, bottom = 15.dp, start = 15.dp)
                    .height(200.dp),
                marker = rememberMarker(),
                chart = lineChart(
                    lines = lineSpec
                ),
                chartScrollState = rememberChartScrollState() ,
                model = chartEntryModel,
                startAxis = rememberStartAxis(
                    axis = axisLineComponent(
                        strokeColor = Color.White,
                        strokeWidth = 2.dp
                    ),
                    itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 5),
                    guideline = axisGuidelineComponent(
                        color = MaterialTheme.colorScheme.onBackground),
                    label = axisLabelComponent(
                        color = Color.White)
                ),
                bottomAxis = rememberBottomAxis(
                    guideline = null,
                    axis = axisLineComponent(
                        strokeColor = Color.White,
                        strokeWidth = 2.dp
                    ),
                    label = axisLabelComponent(
                        lineCount = 1,
                        color = Color.White),
                    valueFormatter = horizontalAxisValueFormatter,
                )
            )
        }
    }

}
