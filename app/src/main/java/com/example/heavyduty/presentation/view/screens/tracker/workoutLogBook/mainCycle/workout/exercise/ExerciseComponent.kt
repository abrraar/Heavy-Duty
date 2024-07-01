package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.example.heavyduty.R
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.view.theme.CardInnerContentBackGround
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.IntractableBackgroundColor
import com.example.heavyduty.presentation.view.theme.NonIntractableBackgroundColor
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
    enableDeleteBtn: Boolean = true,
    exerciseComponentUIState: ExerciseComponentUIState,
    exerciseNameStyle: TextStyle = MaterialTheme.typography.headlineMedium,
)
{
    var intensityComponentClicked by remember { mutableStateOf(false) }
    var deleteExercise by remember { mutableStateOf(false) }
    var showMore by remember { mutableStateOf(false) }

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
        enableDeleteBtn = enableDeleteBtn,
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
                    Body(
                        modifier = Modifier.padding(top = 15.dp),
                        exerciseNumber = exerciseNumber,
                        listOfIntensityUnits = exerciseComponentUIState.listOfIntensityComponentName,
                        exerciseComponentUIState = exerciseComponentUIState,
                        exerciseScreenUIState = exerciseScreenUIState,
                        exerciseEvents = exerciseEvents,
                        exerciseModel = exerciseModel
                    )
                    if(!exerciseScreenUIState.baseCycle){
                        Row(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .width(300.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Row(
                                modifier = Modifier.clickable { showMore = !showMore }
                            ) {
                                Text(
                                    text = if(!showMore){"Show more"}else{"Show less"},
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Icon(
                                    painter = painterResource(
                                        id = if (!showMore) {
                                            R.drawable.arrow_down_icn
                                        } else {
                                            R.drawable.arrow_up_icn
                                        }
                                    ), contentDescription = "arrow")
                            }
                        }
                        if (showMore){
                            Row(
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .width(300.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f)
                                ){
                                    Text(
                                        text = "Total difference :",
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                Row(
                                    modifier = Modifier.weight(1f),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        text = exerciseComponentUIState.totalDifference,
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                    }
                }
                else{
                    Text(
                        modifier = Modifier.padding(top = 15.dp),
                        textAlign = TextAlign.Center,
                        text = "No intensity component selected",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.padding(10.dp))
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
                            if (!exerciseComponentUIState.positiveClicked)
                            {
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

                            if (exerciseComponentUIState.staticHoldClickable && !exerciseComponentUIState.staticClicked) {
                                exerciseEvents(ExerciseEvents.AddIntensityComponent(
                                    intensityUnit = IntensityUnits.Static,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel,
                                ))
                            }
                            else if (exerciseComponentUIState.staticHoldClickable && exerciseComponentUIState.staticClicked)
                            {
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
                                if(exerciseComponentUIState.forcedClicked){
                                    Toast.makeText(context, "de-select forced reps to select static hold", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        // Negative Click
                        2 -> {
                            if (!exerciseComponentUIState.negativeClicked) {

                                exerciseEvents(ExerciseEvents.AddIntensityComponent(
                                    intensityUnit = IntensityUnits.Negative,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel,
                                ))
                            }
                            else  {
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
                            if (exerciseComponentUIState.forcedClickable &&
                                !exerciseComponentUIState.forcedClicked &&
                                !exerciseComponentUIState.positiveClicked &&
                                !exerciseComponentUIState.staticClicked)
                            {
                                exerciseEvents(ExerciseEvents.AddIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel,
                                ))
                            }
                            else if (exerciseComponentUIState.forcedClickable && exerciseComponentUIState.forcedClicked) {
                                //Delete Static
                                exerciseEvents(ExerciseEvents.DeleteIntensityComponent(
                                    intensityUnit = IntensityUnits.Forced,
                                    cycleNumber = exerciseScreenUIState.cycleIndex,
                                    workoutNumber = exerciseScreenUIState.workoutIndex,
                                    exerciseModel = exerciseModel
                                ))
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
            .clip(shape = RoundedCornerShape(20.dp))
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
                .clip(shape = RoundedCornerShape(20.dp))
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

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    listOfIntensityUnits: List<IntensityUnits> = emptyList(),
    exerciseComponentUIState: ExerciseComponentUIState,
    exerciseScreenUIState: ExerciseScreenUIState,
    exerciseEvents: (ExerciseEvents) -> Unit,
    exerciseNumber: String = "1",
    exerciseModel: ExerciseModel,
){
    var weight by remember { mutableStateOf(exerciseModel.weight.toString()) }
    var posRep by remember { mutableStateOf(exerciseModel.value[IntensityUnits.Positive].toString()) }
    var statRep by remember { mutableStateOf(exerciseModel.value[IntensityUnits.Static].toString()) }
    var negRep by remember { mutableStateOf(exerciseModel.value[IntensityUnits.Negative].toString()) }
    var forRep by remember { mutableStateOf(exerciseModel.value[IntensityUnits.Forced].toString()) }
    var preRep by remember { mutableStateOf(exerciseModel.value[IntensityUnits.PreExhaust].toString()) }
    val pattern = Pattern.compile("^(\\d{0,4}+)?([.]?\\d{0,2}?$)")
    val charLimit = 2
    val staticLimit = 3
    val height = (listOfIntensityUnits.size * 60) + 120

    Row(
        modifier = modifier
            .width(300.dp)
            .height(height.dp)
            .background(color = CardInnerContentBackGround, shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier.weight(1.5f),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(60.dp)
                    .padding(bottom = 2.dp)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Unit",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(60.dp)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Weight",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            LazyColumn(
                userScrollEnabled = false
            ) {
                // List Intensity selected
                items(listOfIntensityUnits){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(60.dp)
                            .padding(top = 2.dp,)
                            .background(color = MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(start = 15.dp, end = 15.dp),
                            text = when(it){
                                IntensityUnits.Positive -> "Positive Reps"
                                IntensityUnits.Static -> "Static Hold"
                                IntensityUnits.Negative -> "Negative Reps"
                                IntensityUnits.Forced -> "Forced Reps"
                                IntensityUnits.PreExhaust -> "Pre Exhaust"
                            },
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }

        if(exerciseScreenUIState.baseCycle){
            Column(
                modifier = Modifier.weight(2f)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(60.dp)
                        .padding(start = 2.dp, bottom = 2.dp)
                        .background(color = MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        text = "Value"
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(60.dp)
                        .padding(start = 2.dp)
                        .background(color = NonIntractableBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    CustomTextField(
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
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontColor = MaterialTheme.colorScheme.onPrimary,
                        singleLine = true,
                        placeholderText = "",
                        placeholderStyle = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(1f)
                            .background(
                                color = IntractableBackgroundColor,
                                shape = RoundedCornerShape(0.dp)
                            )
                    )
                }
                LazyColumn(
                    modifier = Modifier,
                    userScrollEnabled = false
                ) {
                    items(listOfIntensityUnits){
                        c ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(60.dp)
                                .padding(start = 2.dp, top = 2.dp)
                                .background(color = NonIntractableBackgroundColor),
                            contentAlignment = Alignment.Center
                        ) {
                            CustomTextField(
                                value = when(c){
                                    IntensityUnits.Positive -> posRep
                                    IntensityUnits.Static -> statRep
                                    IntensityUnits.Negative -> negRep
                                    IntensityUnits.Forced -> forRep
                                    IntensityUnits.PreExhaust -> preRep
                                },
                                onValueChange = {
                                        text ->
                                    when(c){
                                        IntensityUnits.Positive -> {
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
                                            intensityUnit = c,
                                            exerciseModel = exerciseModel,
                                            cycleNumber = exerciseScreenUIState.cycleIndex,
                                            workoutNumber = exerciseScreenUIState.workoutIndex,
                                            reps = when(c){
                                                IntensityUnits.Positive -> posRep
                                                IntensityUnits.Static -> statRep
                                                IntensityUnits.Negative -> negRep
                                                IntensityUnits.Forced -> forRep
                                                IntensityUnits.PreExhaust -> preRep
                                            }
                                        )
                                    )
                                },
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontColor = MaterialTheme.colorScheme.onPrimary,
                                singleLine = true,
                                placeholderText = "",
                                placeholderStyle = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(1f)
                                    .background(
                                        color = IntractableBackgroundColor,
                                        shape = RoundedCornerShape(0.dp)
                                    )
                            )
                        }
                    }
                }
            }
        }
        else{
            LazyRow (
                modifier = Modifier.weight(2f)
            ){
                items(3){
                        r ->
                    LazyColumn(
                        modifier = Modifier,
                        userScrollEnabled = false
                    ) {
                        // Header
                        item {
                            Box(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(60.dp)
                                    .padding(start = 2.dp, bottom = 2.dp)
                                    .background(color = MaterialTheme.colorScheme.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(10.dp),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    text = when(r){
                                        0 -> "Value"
                                        1 -> "Previous Value"
                                        2 -> "Difference"
                                        else -> ""
                                    }
                                )
                            }
                            // Weight
                            Box(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(60.dp)
                                    .padding(start = 2.dp)
                                    .background(color = NonIntractableBackgroundColor),
                                contentAlignment = Alignment.Center
                            ) {
                                when(r){
                                    0 -> {
                                        CustomTextField(
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
                                            imeAction = ImeAction.Done,
                                            keyboardType = KeyboardType.Number,
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                            fontColor = MaterialTheme.colorScheme.onPrimary,
                                            singleLine = true,
                                            placeholderText = "",
                                            placeholderStyle = MaterialTheme.typography.headlineSmall,
                                            modifier = Modifier
                                                .fillMaxWidth(1f)
                                                .fillMaxHeight(1f)
                                                .background(
                                                    color = IntractableBackgroundColor,
                                                    shape = RoundedCornerShape(0.dp)
                                                )
                                        )
                                    }
                                    1 -> {
                                        Text(
                                            text = exerciseComponentUIState.previousWeight,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.titleLarge,
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                    2 -> {
                                        Text(
                                            text = exerciseComponentUIState.weightDifference,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.titleLarge,
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }
                            }
                        }
                        // Intensity selected
                        items(listOfIntensityUnits){
                                c ->
                            Box(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(60.dp)
                                    .padding(start = 2.dp, top = 2.dp)
                                    .background(color = NonIntractableBackgroundColor),
                                contentAlignment = Alignment.Center
                            ) {
                                when(r){
                                    0 -> {
                                        CustomTextField(
                                            value = when(c){
                                                IntensityUnits.Positive -> posRep
                                                IntensityUnits.Static -> statRep
                                                IntensityUnits.Negative -> negRep
                                                IntensityUnits.Forced -> forRep
                                                IntensityUnits.PreExhaust -> preRep
                                            },
                                            onValueChange = {
                                                    text ->
                                                when(c){
                                                    IntensityUnits.Positive -> {
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
                                                        intensityUnit = c,
                                                        exerciseModel = exerciseModel,
                                                        cycleNumber = exerciseScreenUIState.cycleIndex,
                                                        workoutNumber = exerciseScreenUIState.workoutIndex,
                                                        reps = when(c){
                                                            IntensityUnits.Positive -> posRep
                                                            IntensityUnits.Static -> statRep
                                                            IntensityUnits.Negative -> negRep
                                                            IntensityUnits.Forced -> forRep
                                                            IntensityUnits.PreExhaust -> preRep
                                                        }
                                                    )
                                                )
                                            },
                                            imeAction = ImeAction.Done,
                                            keyboardType = KeyboardType.Number,
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                            fontColor = MaterialTheme.colorScheme.onPrimary,
                                            singleLine = true,
                                            placeholderText = "",
                                            placeholderStyle = MaterialTheme.typography.headlineSmall,
                                            modifier = Modifier
                                                .fillMaxWidth(1f)
                                                .fillMaxHeight(1f)
                                                .background(
                                                    color = IntractableBackgroundColor,
                                                    shape = RoundedCornerShape(0.dp)
                                                )
                                        )
                                    }
                                    // Previous Reps
                                    1 -> {
                                        Text(text = when(c){
                                            IntensityUnits.Positive -> { exerciseComponentUIState.positivePreviousReps }
                                            IntensityUnits.Static -> { exerciseComponentUIState.staticPreviousReps }
                                            IntensityUnits.Negative -> { exerciseComponentUIState.negativePreviousReps }
                                            IntensityUnits.Forced -> { exerciseComponentUIState.forcedPreviousReps }
                                            IntensityUnits.PreExhaust -> { exerciseComponentUIState.preExhaustPreviousReps }
                                        },
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.titleLarge,
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                    // Difference
                                    2 -> {
                                        Text(text = when(c){
                                            IntensityUnits.Positive -> { exerciseComponentUIState.positiveDifference }
                                            IntensityUnits.Static -> { exerciseComponentUIState.staticDifference }
                                            IntensityUnits.Negative -> { exerciseComponentUIState.negativeDifference }
                                            IntensityUnits.Forced -> { exerciseComponentUIState.forcedDifference }
                                            IntensityUnits.PreExhaust -> { "" }
                                        },
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.titleLarge,
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BodyPreview(){
    Body(
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
        listOfIntensityUnits = emptyList(),
        exerciseComponentUIState = ExerciseComponentUIState(),
        exerciseEvents = {},
        exerciseScreenUIState = ExerciseScreenUIState()
        )
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

