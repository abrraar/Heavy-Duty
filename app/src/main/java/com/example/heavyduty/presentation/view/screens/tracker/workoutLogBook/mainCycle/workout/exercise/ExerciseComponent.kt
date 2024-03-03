package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise


import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.view.theme.CardInnerContentBackGround
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.IntractableBackgroundColor
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.util.customCard.CustomCard
import com.example.heavyduty.presentation.view.util.customTextField.CustomTextField
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookViewModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentViewModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState
import com.example.heavyduty.units.IntensityUnits

@Composable
fun ExerciseComponent(
    exerciseModel: ExerciseModel? = null,
    workoutLogbookViewModel: WorkoutLogbookViewModel? = null,
    showDetails: Boolean = true,
    exerciseScreenUIState: ExerciseScreenUIState? = null,
    exerciseNameStyle: TextStyle = MaterialTheme.typography.headlineMedium,
){

    var intensityComponentClicked by remember { mutableStateOf(false) }

    val exerciseComponentViewModel = remember { ExerciseComponentViewModel(exerciseModel!!) }
    val exerciseComponentUIState by exerciseComponentViewModel.exerciseComponentUIState.collectAsState()

    CustomCard(
        header = "Exercise ${exerciseModel!!.exerciseNumber}",
        textAlign = Alignment.Start,
        modifier = Modifier
            .padding(top = 10.dp)
    ){
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
            text = exerciseModel.exerciseName,
            color = MaterialTheme.colorScheme.onPrimary,
            style = exerciseNameStyle)

        if(showDetails){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                )){

                CustomButton(
                    addShadow = false,
                    shape = if(intensityComponentClicked) {
                        RoundedCornerShape(
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp,
                            topStart = 20.dp,
                            topEnd = 20.dp) }
                    else{ ButtonDefaults.shape },
                    modifier = Modifier
                        .height(40.dp)
                        .width(280.dp),
                    text = "Add Intensity ",
                    onClick = { intensityComponentClicked = !intensityComponentClicked},
                    style = MaterialTheme.typography.titleLarge
                )
                if (intensityComponentClicked){
                    IntensityExtension(
                        exerciseModel = exerciseModel,
                        events = exerciseComponentViewModel::onExerciseComponentEvent,
                        workoutLogbookViewModel = workoutLogbookViewModel!!,
                        modifier = Modifier.padding(bottom = 15.dp),
                        exerciseScreenUIState = exerciseScreenUIState!!,
                        exerciseComponentUIState = exerciseComponentUIState)
                }
                if(exerciseComponentUIState.listOfIntensityComponentName.size > 0){
                    Body(
                        exerciseComponentUIState = exerciseComponentUIState,
                        modifier = Modifier.padding(top = 10.dp))
                }
                else{
                    Text(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        textAlign = TextAlign.Center,
                        text = "No intensity component is selected",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleSmall)
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }

    }
}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    exerciseComponentUIState: ExerciseComponentUIState,
    ){
    Column(
        modifier = modifier
            .width(280.dp)
            .height(((50 * (exerciseComponentUIState.listOfIntensityComponentName.size + 1)) + (exerciseComponentUIState.listOfIntensityComponentName.size * 2.5)).dp)
            .background(
                color = CardInnerContentBackGround,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        HeaderRow()
        LazyColumn(userScrollEnabled = false){
            items(exerciseComponentUIState.listOfIntensityComponentName.size){
                BodyRow(
                    intensityComponentText = exerciseComponentUIState.listOfIntensityComponentName[it].component,
                    bottomCorner = if(it +1 == exerciseComponentUIState.listOfIntensityComponentName.size){ 20 } else{ 0 }
                )
            }
        }
    }
}

@Composable
private fun HeaderRow(){
    LazyRow(
        userScrollEnabled = false,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))){
        items(4)
        {
            Box(modifier = Modifier
                .height(50.dp)
                .width(70.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    text = when(it){
                        0 -> "Intensity component"
                        1 -> "value"
                        2 -> "previous\nvalue"
                        3 -> "increase\nrate %"
                        else -> "lol"
                    },
                    color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}


@Composable
private fun BodyRow(
    intensityComponentText: String =  "",
    previousValue: String = "",
    increaseRate: String = "",
    bottomCorner: Int = 20
){
    LazyRow(
        userScrollEnabled = false,
        modifier = Modifier
            .padding(top = 2.dp)) {
        items(4){
            Box(modifier = Modifier
                .background(
                    color = when (it) {
                        0 -> MaterialTheme.colorScheme.primary
                        else -> Color.DarkGray
                    },
                    shape = RoundedCornerShape(
                        bottomEnd = if (it == 3) {
                            bottomCorner.dp
                        } else {
                            0.dp
                        },
                        bottomStart = if (it == 0) {
                            bottomCorner.dp
                        } else {
                            0.dp
                        }
                    )
                )
                .height(50.dp)
                .width(70.dp),
                contentAlignment = Alignment.Center)
            {
                when(it){
                    1 -> CustomTextField(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontColor = MaterialTheme.colorScheme.onPrimary,
                        singleLine = true,
                        placeholderText = "",
                        placeholderStyle = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .height(50.dp)
                            .width(70.dp)
                            .background(
                                color = IntractableBackgroundColor,
                                shape = RoundedCornerShape(0.dp)
                            )
                    )
                    else ->  Text(
                        text = when(it){
                            0 -> intensityComponentText
                            2 -> previousValue
                            3 -> increaseRate
                            else -> ""
                        },
                        textAlign = TextAlign.Center,
                        style =  when(it){
                            0 -> MaterialTheme.typography.labelSmall
                            else -> MaterialTheme.typography.headlineSmall
                        },
                        color = when(it){
                            0 -> MaterialTheme.colorScheme.onPrimary
                            else -> MaterialTheme.colorScheme.onBackground
                        })
                }
            }
        }
    }
}


@Composable
private fun IntensityExtension(
    modifier: Modifier = Modifier,
    workoutLogbookViewModel: WorkoutLogbookViewModel,
    exerciseModel: ExerciseModel? = null,
    events: (ExerciseComponentEvents) -> Unit,
    exerciseScreenUIState: ExerciseScreenUIState,
    exerciseComponentUIState: ExerciseComponentUIState,
){
    val context = LocalContext.current

    LazyColumn(
        userScrollEnabled = false,
        modifier = modifier
            .width(280.dp)
            .height(620.dp)
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(5){

            IntensityComponent(
                onClick = {
                    when(it){
                        // Positive Click
                        0 -> {
                            if (!exerciseComponentUIState.positiveClicked) {
                                // update UI state
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Positive
                                    )
                                )

                                // data persist
                                workoutLogbookViewModel.deleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Positive,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )

                                workoutLogbookViewModel.deleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                )


                            }
                            else
                            {
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Positive
                                    )
                                )

                                workoutLogbookViewModel.addIntensityComponent(
                                    intensityUnit = IntensityUnits.Positive,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )
                            }
                        }
                        // Static Click
                        1 -> {
                            if (exerciseComponentUIState.staticHoldClickable && !exerciseComponentUIState.staticClicked) {
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Static
                                    )
                                )
                                // Add static
                                workoutLogbookViewModel.addIntensityComponent(
                                    intensityUnit = IntensityUnits.Static,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )

                            }
                            if (exerciseComponentUIState.staticHoldClickable && exerciseComponentUIState.staticClicked)
                            {
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Static
                                    )
                                )
                                //Delete Static
                                workoutLogbookViewModel.deleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Static,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )
                            }
                            else
                            {
                                if(exerciseComponentUIState.forcedClicked){
                                    Toast.makeText(context, "de-select forced reps to select static hold", Toast.LENGTH_SHORT).show()
                                }

                            }
                        }
                        // Negative Click
                        2 -> {
                            if (!exerciseComponentUIState.negativeClicked) {
                                Log.i("negative click in add", exerciseComponentUIState.negativeClicked.toString())
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Negative
                                    )
                                )

                                workoutLogbookViewModel.addIntensityComponent(
                                    intensityUnit = IntensityUnits.Negative,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )
                            }
                            else  {
                                Log.i("negative click in delete", exerciseComponentUIState.negativeClicked.toString())
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Negative
                                    )
                                )
                                workoutLogbookViewModel.deleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Negative,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )
                            }
                        }
                        // Forced Click
                        3 -> {
                            if (exerciseComponentUIState.forcedClickable && !exerciseComponentUIState.forcedClicked && !exerciseComponentUIState.positiveClicked) {
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Forced
                                    )
                                )
                                workoutLogbookViewModel.addIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )
                            }
                            if (exerciseComponentUIState.forcedClickable && exerciseComponentUIState.forcedClicked) {
                                events(
                                    ExerciseComponentEvents.IntensityComponentClicked(
                                        IntensityUnits.Forced
                                    )
                                )
                                workoutLogbookViewModel.deleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel!!
                                )
                            }
                            else {
                                if(exerciseComponentUIState.positiveClicked){
                                    Toast.makeText(context, "need positive reps\nto perform forced reps", Toast.LENGTH_SHORT).show()
                                }
                                if(exerciseComponentUIState.staticClicked){
                                    Toast.makeText(context, "de-select static to select forced", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        //Pre Exhaust Click
                        4 -> {
                            if (exerciseComponentUIState.preExhaustClickable) {

//                            workoutLogbookViewModel.addIntensityComponent(
//                                intensityUnit = IntensityUnits.PreExhaust,
//                                cycleNumber = exerciseScreenUIState.cycleIndex,
//                                workoutNumber = exerciseScreenUIState.workoutIndex,
//                                exerciseModel = exerciseModel!!
//                            )
                            } else {
                                Toast.makeText(
                                    context,
                                    "cannot select preExhaust",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                },
                modifier = Modifier.padding(top = 20.dp),
                selectedIndicator = when(it){
                    0 -> exerciseComponentUIState.positiveText
                    1 -> exerciseComponentUIState.staticHoldText
                    2 -> exerciseComponentUIState.negativeText
                    3 -> exerciseComponentUIState.forcedText
                    4 -> exerciseComponentUIState.preExhaustText
                    else -> ""
                },
                color = when(it){
                    0 -> exerciseComponentUIState.positiveRepColor
                    1 -> exerciseComponentUIState.staticHoldColor
                    2 -> exerciseComponentUIState.negativeColor
                    3 -> exerciseComponentUIState.forcedColor
                    4 -> exerciseComponentUIState.preExhaustColor
                    else -> Color.Green },
                intensityComponentName = when(it){
                    0 -> "Positive Reps"
                    1 -> "Static Hold"
                    2 -> "Negative Reps"
                    3 -> "Forced Reps"
                    4 -> "Pre Exhaust"
                    else -> ""
                }
            )
        }

    }
}

@Composable
private fun IntensityComponent(
    modifier:  Modifier = Modifier,
    onClick: () -> Unit,
    intensityComponentName: String = "intensity Name",
    selectedIndicator: String = "Add",
    color: Color = Green
){
    Column(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .shadow(20.dp, shape = RoundedCornerShape(20.dp))
            .background(
                color = color,
                shape = RoundedCornerShape(20.dp)
            )
            .height(100.dp)
            .width(220.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(20.dp)
                )
                .height(60.dp)
                .width(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )  {
            Text(
                text = intensityComponentName,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge)
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = selectedIndicator,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.titleLarge)

    }
}


@Preview
@Composable
private fun ExerciseComponentPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        ExerciseComponent(
            exerciseScreenUIState = ExerciseScreenUIState(),
           )
    }
}
@Preview
@Composable
private fun IntensityComponentPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        IntensityExtension(
            events = {},
            exerciseScreenUIState = ExerciseScreenUIState(),
            workoutLogbookViewModel = hiltViewModel(),
            exerciseComponentUIState = ExerciseComponentUIState(),)
    }
}

@Preview
@Composable
private fun IntensityExtensionPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        IntensityComponent(onClick = {})
    }
}
@Preview
@Composable
private fun BodyRowPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        BodyRow()
    }
}


@Preview
@Composable
private fun BodyPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        Body(exerciseComponentUIState = ExerciseComponentUIState())
    }
}