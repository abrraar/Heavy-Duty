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
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.view.theme.CardInnerContentBackGround
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.IntractableBackgroundColor
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.util.customCard.CustomCard
import com.example.heavyduty.presentation.view.util.customTextField.CustomTextField
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.ExerciseEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState
import com.example.heavyduty.units.IntensityUnits
import java.util.regex.Pattern

@Composable
inline fun <reified VM : ViewModel> hiltViewModelWithKey(key: String): VM {
    val viewModelStoreOwner =
        if (checkNotNull(LocalViewModelStoreOwner.current) is NavBackStackEntry) {
            checkNotNull(LocalViewModelStoreOwner.current) { "ViewModelStoreOwner is null" }
        } else null

    return viewModel(
        key = key,
        factory = if (viewModelStoreOwner is NavBackStackEntry) {
            HiltViewModelFactory(
                LocalContext.current,
                viewModelStoreOwner
            )
        } else null
    )
}

@Composable
fun ExerciseComponent(
    exerciseModel: ExerciseModel,
    showDetails: Boolean = true,
    exerciseNumber: String = "1",
    exerciseScreenUIState: ExerciseScreenUIState,
    exerciseEvents: (ExerciseEvents) -> Unit,
    exerciseComponentUIState: ExerciseComponentUIState,
    exerciseNameStyle: TextStyle = MaterialTheme.typography.headlineMedium,
)
{
    var intensityComponentClicked by remember { mutableStateOf(false) }
    var deleteExercise by remember { mutableStateOf(false) }

    if(deleteExercise){
        val context  = LocalContext.current
        Prompt(
            titleText = "Delete Exercise",
            message = "Do you want to delete\n" + exerciseModel.exerciseName,
            onConfirm = {
                exerciseEvents(ExerciseEvents.DeleteExercise(
                    cycleNumber = exerciseScreenUIState.cycleIndex,
                    workoutNumber = exerciseScreenUIState.workoutIndex,
                    exerciseModel = exerciseModel
                ))
                deleteExercise = false
                Toast.makeText(context, "${exerciseModel.exerciseName} deleted", Toast.LENGTH_SHORT).show()
            },
            onCancel = {
                deleteExercise = false
            }
        )
    }

    CustomCard(
        enableDeleteBtn = true,
        deleteBtn = { deleteExercise = true },
        header = "Exercise $exerciseNumber" ,
        textAlign = Alignment.Start,
        modifier = Modifier
            .padding(top = 15.dp, bottom = 15.dp)
    )
    {
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
                        .width(300.dp),
                    text = "Add Intensity ",
                    onClick = { intensityComponentClicked = !intensityComponentClicked},
                    style = MaterialTheme.typography.titleLarge
                )

                if (intensityComponentClicked){
                    IntensityExtension(
                        exerciseModel = exerciseModel,
                        exerciseEvents = exerciseEvents,
                        modifier = Modifier.padding(bottom = 15.dp),
                        exerciseScreenUIState = exerciseScreenUIState,
                        exerciseComponentUIState = exerciseComponentUIState)
                }

                if(exerciseComponentUIState.listOfIntensityComponentName.size > 0){
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .width(300.dp)
                            .height((104 + (exerciseComponentUIState.listOfIntensityComponentName.size * 50) + (exerciseComponentUIState.listOfIntensityComponentName.size)).dp)
                            .background(
                                color = CardInnerContentBackGround,
                                shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        HeaderRow()
                        var weight by remember { mutableStateOf(exerciseModel.weight.toString()) }
                        val pattern = Pattern.compile("^(\\d{0,4}+)?([.]?\\d{0,2}?$)")
                        BodyRow(
                            value = weight,
                            onValueChange = {
                                val matcher = pattern.matcher(it)
                                if(it != "."){
                                    if(matcher.matches()){
                                        weight = it
                                    }
                                }
                                exerciseEvents(ExerciseEvents.AddWeight(
                                    index = exerciseNumber.toInt().minus(1),
                                    weight = weight,
                                    cycleIndex = exerciseScreenUIState.cycleIndex,
                                    workoutIndex = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                ))
                            },
                            previousValue = exerciseComponentUIState.previousWeight,
                            difference = exerciseComponentUIState.weightDifference,
                            keyboardType = KeyboardType.Decimal,
                            intensityComponentText = "Weight",
                            bottomCorner = 0
                        )
                        LazyColumn(userScrollEnabled = false){
                            items(exerciseComponentUIState.listOfIntensityComponentName.size)
                            {
                                val intensityUnit = exerciseComponentUIState.listOfIntensityComponentName[it]
                                var posRep by remember {
                                    mutableStateOf(exerciseModel.value[IntensityUnits.Positive].toString())
                                }
                                var statRep by remember {
                                    mutableStateOf(exerciseModel.value[IntensityUnits.Static].toString())
                                }
                                var negRep by remember {
                                    mutableStateOf(exerciseModel.value[IntensityUnits.Negative].toString())
                                }
                                var forRep by remember {
                                    mutableStateOf(exerciseModel.value[IntensityUnits.Forced].toString())
                                }
                                var preRep by remember {
                                    mutableStateOf(exerciseModel.value[IntensityUnits.PreExhaust].toString())
                                }
                                val charLimit = 2
                                val staticLimit = 3
                                BodyRow(
                                    value = when(intensityUnit){
                                        IntensityUnits.Positive -> posRep
                                        IntensityUnits.Static -> statRep
                                        IntensityUnits.Negative -> negRep
                                        IntensityUnits.Forced -> forRep
                                        IntensityUnits.PreExhaust -> preRep
                                    },
                                    onValueChange = {
                                        text ->
                                        when(intensityUnit){
                                            IntensityUnits.Positive -> {
                                                Log.i("text", text.length.toString())
                                                if (text.length <= charLimit) {
                                                    posRep = text
                                                }
                                            }

                                            IntensityUnits.Static -> {
                                                if(text.length <= staticLimit){
                                                    statRep = text
                                                }

                                            }
                                            IntensityUnits.Negative -> {
                                                if(text.length <= charLimit){
                                                    negRep = text
                                                }

                                            }
                                            IntensityUnits.Forced -> {
                                                if(text.length <= charLimit){
                                                    forRep = text
                                                }

                                            }
                                            IntensityUnits.PreExhaust -> {
                                                if(text.length <= charLimit){
                                                    preRep = text
                                                }

                                            }
                                        }
                                        exerciseEvents(
                                            ExerciseEvents.AddReps(
                                                intensityUnit = intensityUnit,
                                                exerciseModel = exerciseModel,
                                                cycleNumber = exerciseScreenUIState.cycleIndex,
                                                workoutNumber = exerciseScreenUIState.workoutIndex,
                                                reps = when(intensityUnit ){
                                                    IntensityUnits.Positive -> posRep
                                                    IntensityUnits.Static -> statRep
                                                    IntensityUnits.Negative -> negRep
                                                    IntensityUnits.Forced -> forRep
                                                    IntensityUnits.PreExhaust -> preRep
                                                }
                                            )
                                        )
                                    },
                                    previousValue = when(intensityUnit){
                                        IntensityUnits.Positive -> { exerciseComponentUIState.positivePreviousReps }
                                        IntensityUnits.Static -> { exerciseComponentUIState.staticPreviousReps }
                                        IntensityUnits.Negative -> { exerciseComponentUIState.negativePreviousReps }
                                        IntensityUnits.Forced -> { exerciseComponentUIState.forcedPreviousReps }
                                        IntensityUnits.PreExhaust -> { exerciseComponentUIState.preExhaustPreviousReps }
                                    },
                                    difference = when(intensityUnit){
                                        IntensityUnits.Positive -> { exerciseComponentUIState.positiveDifference }
                                        IntensityUnits.Static -> { exerciseComponentUIState.staticDifference }
                                        IntensityUnits.Negative -> { exerciseComponentUIState.negativeDifference }
                                        IntensityUnits.Forced -> { exerciseComponentUIState.forcedDifference }
                                        IntensityUnits.PreExhaust -> { "" }
                                    },
                                    intensityComponentText = exerciseComponentUIState.listOfIntensityComponentName[it].component,
                                    bottomCorner = if(it +1 == exerciseComponentUIState.listOfIntensityComponentName.size){ 20 } else{ 0 }
                                )
                            }
                        }
                    }
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
                .width(75.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    text = when(it){
                        0 -> "Units"
                        1 -> "Value"
                        2 -> "Previous\nvalue"
                        3 -> "Difference"
                        else -> "lol"
                    },
                    color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Composable
private fun BodyRow(
    value: String = "",
    keyboardType: KeyboardType = KeyboardType.NumberPassword,
    onValueChange: (String) -> Unit,
    intensityComponentText: String =  "",
    previousValue: String = "",
    difference: String = "",
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
                .width(75.dp),
                contentAlignment = Alignment.Center)
            {
                when(it){
                    1 -> CustomTextField(
                        value = value,
                        onValueChange = onValueChange,
                        imeAction = ImeAction.Done,
                        keyboardType = keyboardType,
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
                            3 -> difference
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
    exerciseModel: ExerciseModel,
    exerciseEvents: (ExerciseEvents) -> Unit,
    exerciseScreenUIState: ExerciseScreenUIState,
    exerciseComponentUIState: ExerciseComponentUIState,
){
    val context = LocalContext.current

    LazyColumn(
        userScrollEnabled = false,
        modifier = modifier
            .width(300.dp)
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
                            if (!exerciseComponentUIState.positiveClicked.value)
                            {
                                // Removing positive
//                                exerciseComponentUIState.positiveRepColor.value = Green
//                                exerciseComponentUIState.positiveText.value = "Add"
//                                exerciseComponentUIState.positiveClicked.value = true
//
//                                exerciseComponentUIState.forcedColor.value = Color.Red
//                                exerciseComponentUIState.forcedText.value = if(exerciseComponentUIState.staticClicked.value){"Static selected"}else{"Need Positive"}
//                                exerciseComponentUIState.forcedClickable.value = false
//                                exerciseComponentUIState.forcedClicked.value = false
//
//                                exerciseComponentUIState.listOfIntensityComponentName.remove(IntensityUnits.Positive)
                                // data persist
                                exerciseEvents(ExerciseEvents.DeleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Positive,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                ))

                                exerciseEvents(ExerciseEvents.DeleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                ))

                            }
                            else
                            {
                                // Adding Positive
//                                exerciseComponentUIState.positiveRepColor.value = BrightGreen
//                                exerciseComponentUIState.positiveText.value = "Added"
//                                exerciseComponentUIState.positiveClicked.value = false
//                                exerciseComponentUIState.listOfIntensityComponentName.add(IntensityUnits.Positive)
//
//                                if(exerciseComponentUIState.staticClicked.value)
//                                {
//                                    exerciseComponentUIState.forcedColor.value = Color.Red
//                                    exerciseComponentUIState.forcedText.value = "Static selected"
//                                    exerciseComponentUIState.forcedClickable.value = false
//                                    exerciseComponentUIState.forcedClicked.value = false
//                                }
//                                else
//                                {
//                                    exerciseComponentUIState.forcedColor.value = Green
//                                    exerciseComponentUIState.forcedText.value = "Add"
//                                    exerciseComponentUIState.forcedClickable.value = true
//                                    exerciseComponentUIState.forcedClicked.value = false
//                                }
                                // Update Database
                                exerciseEvents(ExerciseEvents.AddIntensityComponent(
                                    intensityUnit = IntensityUnits.Positive,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel,
                                ))

                            }
                        }
                        // Static Click
                        1 -> {

                            if (exerciseComponentUIState.staticHoldClickable.value && !exerciseComponentUIState.staticClicked.value) {
                                // Adding Static
//                                exerciseComponentUIState.staticHoldColor.value = BrightGreen
//                                exerciseComponentUIState.staticHoldText.value = "Added"
//                                exerciseComponentUIState.staticClicked.value = true
//                                exerciseComponentUIState.listOfIntensityComponentName.forEach { it -> Log.i("list component", it.component) }
//
//                                exerciseComponentUIState.forcedColor.value = Color.Red
//                                exerciseComponentUIState.forcedText.value = "Static selected"
//                                exerciseComponentUIState.forcedClickable.value = false
//
//                                exerciseComponentUIState.listOfIntensityComponentName.add(IntensityUnits.Static)

                                exerciseEvents(ExerciseEvents.AddIntensityComponent(
                                    intensityUnit = IntensityUnits.Static,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel,
                                ))


                            }
                            else if (exerciseComponentUIState.staticHoldClickable.value && exerciseComponentUIState.staticClicked.value)
                            {
//                                exerciseComponentUIState.staticHoldColor.value = Green
//                                exerciseComponentUIState.staticHoldText.value = "Add"
//                                exerciseComponentUIState.staticClicked.value = false
//                                exerciseComponentUIState.listOfIntensityComponentName.remove(IntensityUnits.Static)
//
//                                if(exerciseComponentUIState.listOfIntensityComponentName.contains(IntensityUnits.Positive))
//                                {
//                                    exerciseComponentUIState.forcedColor.value = Green
//                                    exerciseComponentUIState.forcedText.value = "Add"
//                                    exerciseComponentUIState.forcedClickable.value = true
//                                }
//                                else
//                                {
//                                    exerciseComponentUIState.forcedColor.value = Color.Red
//                                    exerciseComponentUIState.forcedText.value = "Need Positive"
//                                    exerciseComponentUIState.forcedClickable.value = false
//                                    exerciseComponentUIState.forcedClicked.value = false
//
//                                }

                                //Delete Static
                                exerciseEvents(ExerciseEvents.DeleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Static,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                ))

                            }
                            else
                            {
                                if(exerciseComponentUIState.forcedClicked.value){
                                    Toast.makeText(context, "de-select forced reps to select static hold", Toast.LENGTH_SHORT).show()
                                }

                            }
                        }
                        // Negative Click
                        2 -> {
                            if (!exerciseComponentUIState.negativeClicked.value) {
//                                exerciseComponentEvents(
//                                    ExerciseComponentEvents.IntensityComponentClicked(
//                                        IntensityUnits.Negative
//                                    )
//                                )

                                exerciseEvents(ExerciseEvents.AddIntensityComponent(
                                    intensityUnit = IntensityUnits.Negative,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel,
                                ))
                            }
                            else  {
//                                exerciseComponentEvents(
//                                    ExerciseComponentEvents.IntensityComponentClicked(
//                                        IntensityUnits.Negative
//                                    )
//                                )
                                //Delete Static
                                exerciseEvents(ExerciseEvents.DeleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Negative,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                ))
                            }
                        }
                        // Forced Click
                        3 -> {

                            if (exerciseComponentUIState.forcedClickable.value &&
                                !exerciseComponentUIState.forcedClicked.value &&
                                !exerciseComponentUIState.positiveClicked.value &&
                                !exerciseComponentUIState.staticClicked.value)
                            {
//                                exerciseComponentEvents(
//                                    ExerciseComponentEvents.IntensityComponentClicked(
//                                        IntensityUnits.Forced
//                                    )
//                                )
                                Log.i("force", "added")
                                exerciseEvents(ExerciseEvents.AddIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel,
                                ))
                            }
                            else if (exerciseComponentUIState.forcedClickable.value && exerciseComponentUIState.forcedClicked.value) {
//                                exerciseComponentEvents(
//                                    ExerciseComponentEvents.IntensityComponentClicked(
//                                        IntensityUnits.Forced
//                                    )
//                                )
                                Log.i("force", "delete")
                                //Delete Static
                                exerciseEvents(ExerciseEvents.DeleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                ))
                            }
                            else {
                                if(exerciseComponentUIState.positiveClicked.value){
                                    Toast.makeText(context, "need positive reps\nto perform forced reps", Toast.LENGTH_SHORT).show()
                                }
                                if(exerciseComponentUIState.staticClicked.value){
                                    Toast.makeText(context, "de-select static to select forced", Toast.LENGTH_SHORT).show()
                                }
                            }
                            Log.i("force", "nothing")
                            Log.i("force clickable", exerciseComponentUIState.forcedClickable.value.toString())
                            Log.i("force click", exerciseComponentUIState.forcedClicked.value.toString())
                            Log.i("positive click", exerciseComponentUIState.positiveClicked.value.toString())
                            Log.i("static click", exerciseComponentUIState.staticClicked.value.toString())
                        }
                        //Pre Exhaust Click
                        4 -> {
                            if (exerciseComponentUIState.preExhaustClickable) {
                                Toast.makeText(
                                    context,
                                    "cannot select preExhaust",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else {
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
                    0 -> exerciseComponentUIState.positiveText.value
                    1 -> exerciseComponentUIState.staticHoldText.value
                    2 -> exerciseComponentUIState.negativeText.value
                    3 -> exerciseComponentUIState.forcedText.value
                    4 -> exerciseComponentUIState.preExhaustText
                    else -> ""
                },
                color = when(it){
                    0 -> exerciseComponentUIState.positiveRepColor
                    1 -> exerciseComponentUIState.staticHoldColor.value
                    2 -> exerciseComponentUIState.negativeColor.value
                    3 -> exerciseComponentUIState.forcedColor.value
                    4 -> exerciseComponentUIState.preExhaustColor.value
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
    ExerciseComponent(
        exerciseModel = ExerciseModel(
            exerciseNumber = 1,
            exerciseName = "Exercise Preview",
            exerciseType = "Compound",
            weight = 0.0,
            intensitySelected = arrayListOf(IntensityUnits.Positive),
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = hashMapOf(IntensityUnits.Positive to 0),
            previousWeight = 0.0
        ),
        exerciseEvents = {},
        exerciseScreenUIState = ExerciseScreenUIState(),
        exerciseComponentUIState = ExerciseComponentUIState()
    )
}

@Preview
@Composable
private fun IntensityExtensionPreview(){
    IntensityExtension(
        exerciseScreenUIState = ExerciseScreenUIState(),
        exerciseComponentUIState = ExerciseComponentUIState(),
        exerciseEvents = {},
        exerciseModel = ExerciseModel(
            exerciseNumber = 1,
            exerciseName = "Exercise Preview",
            exerciseType = "Compound",
            weight = 0.0,
            intensitySelected = arrayListOf(IntensityUnits.Positive),
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = hashMapOf(IntensityUnits.Positive to 0),
            previousWeight = 0.0
        )
    )
}


@Preview
@Composable
private fun IntensityComponentPreview(){
    IntensityComponent(onClick = {})
}


@Preview
@Composable
private fun BodyRowPreview(){
    BodyRow(
        value = "",
        onValueChange = {}
    )

}

@Preview
@Composable
private fun HeaderRowPreview(){
    HeaderRow()
}

