package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.units.IntensityUnits
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class ExerciseComponentUIState(

    val listOfIntensityComponentName: ArrayList<IntensityUnits> = arrayListOf(IntensityUnits.Positive),

    val previousWeight: String = "",

    val positiveRepColor: Color = BrightGreen,
    val positiveText: MutableState<String> = mutableStateOf("Added"),
    val positiveClicked: MutableState<Boolean> = mutableStateOf(false),
    val positivePreviousReps: String = "",

    val staticHoldColor: MutableState<Color> = mutableStateOf(Green),
    val staticHoldText: MutableState<String> = mutableStateOf("Add"),
    val staticHoldClickable: MutableState<Boolean> = mutableStateOf(true),
    val staticClicked: MutableState<Boolean> = mutableStateOf(false),
    val staticPreviousReps: String = "",

    val negativeColor: MutableState<Color> = mutableStateOf(Green),
    val negativeText: MutableState<String> = mutableStateOf("Add"),
    val negativeClicked: MutableState<Boolean> = mutableStateOf(false),
    val negativePreviousReps: String = "",

    val forcedColor: MutableState<Color> = mutableStateOf(Green),
    val forcedText: MutableState<String> = mutableStateOf("Add"),
    val forcedClickable: MutableState<Boolean> = mutableStateOf(true),
    val forcedClicked: MutableState<Boolean> = mutableStateOf(false),
    val forcedPreviousReps: String = "",

    val preExhaustColor: MutableState<Color> = mutableStateOf(Green),
    val preExhaustText: String = "Add",
    val preExhaustClickable: Boolean = true,
    val preExhaustClicked: Boolean = false,
    val preExhaustPreviousReps: String = "",

    val weightDifference: String = "",
    val positiveDifference: String = "",
    val staticDifference: String = "",
    val negativeDifference: String = "",
    val forcedDifference: String = "",

)


private fun addIntensityToList(intensityUnits: IntensityUnits, listOfIntensityComponentName: ArrayList<IntensityUnits>) {
    if (!listOfIntensityComponentName.contains(intensityUnits)){
        listOfIntensityComponentName.add(intensityUnits)
    }
}

private fun removeIntensityFromList(intensityUnits: IntensityUnits, listOfIntensityComponentName: ArrayList<IntensityUnits>) {
    if(listOfIntensityComponentName.contains(intensityUnits)){
        listOfIntensityComponentName.remove(intensityUnits)
    }
}

fun ExerciseModel.toExerciseComponentUIState(): ExerciseComponentUIState {

    val listOfIntensityComponentName = arrayListOf(IntensityUnits.Positive)
    val exerciseComponentUIState = MutableStateFlow(ExerciseComponentUIState())

    fun calculateDifference(newRecord: Double, oldRecord: Double): String {
        val difference = if(newRecord == 0.0 || oldRecord == 0.0){""}else{ "${Math.round((newRecord - oldRecord)/((oldRecord + newRecord)/2)*100)}%" }
        Log.i("newRecord", newRecord.toString())
        Log.i("oldRecord", oldRecord.toString())
        Log.i("difference", difference)
        return difference
    }

//------------------------------- Previous Weight ------------------------------------
    exerciseComponentUIState.update {
        val difference: String = calculateDifference(
            newRecord = weight,
            oldRecord = previousWeight)
        it.copy(
            previousWeight = previousWeight.toString(),
            weightDifference = difference
        )
    }
//------------------------------- Positive --------------------------------------
    exerciseComponentUIState.update {
        val difference: String = calculateDifference(
            newRecord = value[IntensityUnits.Positive]!!.toDouble(),
            oldRecord = previousReps[IntensityUnits.Positive]!!.toDouble())

        it.copy(
            positivePreviousReps = previousReps[IntensityUnits.Positive].toString(),
            positiveDifference = difference
        )
    }
    if (intensitySelected.contains(IntensityUnits.Positive)) {
        addIntensityToList(IntensityUnits.Positive, listOfIntensityComponentName)
        val posRep = value[IntensityUnits.Positive].toString()
        Log.i("pos rep", posRep)
        exerciseComponentUIState.update {
            it.copy(
                positiveRepColor = (BrightGreen) ,
                positiveText = mutableStateOf("Added"),
                positiveClicked = mutableStateOf(false),
                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        // Forced depends on positive therefore it's state will be updated when positive is updated
        // but if static is present forced cannot be selected
        if (intensitySelected.contains(IntensityUnits.Static)) {
            exerciseComponentUIState.update {
                it.copy(
                    forcedText = mutableStateOf("Static Selected"),
                    forcedColor = mutableStateOf(Color.Red),
                    forcedClickable = mutableStateOf(false),
                )
            }
        }
        // if static isn't available and positive is then forced is clickable
        else {
            if (intensitySelected.contains(IntensityUnits.Forced)) {
                addIntensityToList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = mutableStateOf("Added"),
                        forcedColor = mutableStateOf(BrightGreen),
                        forcedClickable = mutableStateOf(true),
                        forcedClicked = mutableStateOf(true)
                    )
                }
            } else {
                removeIntensityFromList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = mutableStateOf("Add"),
                        forcedColor = mutableStateOf(Green),
                        forcedClickable = mutableStateOf(true),
                    )
                }
            }
        }
    }

    // Does not contain positive
    else {
        removeIntensityFromList(IntensityUnits.Positive, listOfIntensityComponentName)
        removeIntensityFromList(IntensityUnits.Forced, listOfIntensityComponentName)

        exerciseComponentUIState.update {
            it.copy(
                positiveRepColor = (Green) ,
                positiveText = mutableStateOf("Add"),
                positiveClicked = mutableStateOf(true),

                forcedText = mutableStateOf("Need Positive Reps"),
                forcedColor = mutableStateOf(Color.Red),
                forcedClickable = mutableStateOf(true),

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }

    }

//-------------------------------Contain static-------------------------------------------
    exerciseComponentUIState.update {
        val difference: String = calculateDifference(
            newRecord = value[IntensityUnits.Static]!!.toDouble(),
            oldRecord = previousReps[IntensityUnits.Static]!!.toDouble())

        it.copy(
            staticPreviousReps = previousReps[IntensityUnits.Static].toString(),
            staticDifference = difference
        )
    }
    if (intensitySelected.contains(IntensityUnits.Static)) {
        addIntensityToList(IntensityUnits.Static, listOfIntensityComponentName)

        exerciseComponentUIState.update {
            it.copy(
                staticHoldColor = mutableStateOf(BrightGreen),
                staticHoldText = mutableStateOf("Added"),
                staticClicked = mutableStateOf(true),

                forcedClickable = mutableStateOf(true),
                forcedColor = mutableStateOf(Color.Red),
                forcedText = mutableStateOf("Static Selected"),

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
    }
    // Doesn't contain Static
    else {
        removeIntensityFromList(IntensityUnits.Static, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                staticHoldColor = mutableStateOf(Green),
                staticHoldText = mutableStateOf("Add"),
                staticClicked = mutableStateOf(false),

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        if (intensitySelected.contains(IntensityUnits.Positive)) {
            if (intensitySelected.contains(IntensityUnits.Forced)) {
                addIntensityToList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = mutableStateOf("Added"),
                        forcedColor = mutableStateOf( BrightGreen),
                        forcedClicked = mutableStateOf(true),
                        forcedClickable = mutableStateOf(true),
                    )
                }
            } else if (!intensitySelected.contains(IntensityUnits.Forced)) {
                removeIntensityFromList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = mutableStateOf("Add"),
                        forcedColor = mutableStateOf(Green),
                        forcedClickable = mutableStateOf(true),
                    )
                }
            }
        } else if (!intensitySelected.contains(IntensityUnits.Positive)) {
            removeIntensityFromList(IntensityUnits.Forced, listOfIntensityComponentName)
            exerciseComponentUIState.update {
                it.copy(
                    forcedText = mutableStateOf("Need Positive"),
                    forcedColor = mutableStateOf(Color.Red),
                    forcedClickable = mutableStateOf(true),
                )
            }
        }

    }

//--------------------------------Negative-------------------------------------
    exerciseComponentUIState.update {
        val difference: String = calculateDifference(
            newRecord = value[IntensityUnits.Negative]!!.toDouble(),
            oldRecord = previousReps[IntensityUnits.Negative]!!.toDouble())

        it.copy(
            negativePreviousReps = previousReps[IntensityUnits.Negative].toString(),
            negativeDifference = difference
        )
    }
    if (intensitySelected.contains(IntensityUnits.Negative)) {
        addIntensityToList(IntensityUnits.Negative, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                negativeColor = mutableStateOf( BrightGreen),
                negativeText = mutableStateOf("Added"),
                negativeClicked = mutableStateOf(true),

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        Log.i(" yes negative", listOfIntensityComponentName.toString())
    } else {
        removeIntensityFromList(IntensityUnits.Negative, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                negativeText = mutableStateOf("Add"),
                negativeColor = mutableStateOf(Green),
                negativeClicked = mutableStateOf(false),

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        Log.i(" no negative", listOfIntensityComponentName.toString())
    }

//-------------------------Forced-------------------------------------
    exerciseComponentUIState.update {
        val difference: String = calculateDifference(
            newRecord = value[IntensityUnits.Forced]!!.toDouble(),
            oldRecord = previousReps[IntensityUnits.Forced]!!.toDouble())

        it.copy(
            forcedPreviousReps = previousReps[IntensityUnits.Forced].toString(),
            forcedDifference = difference
        )
    }
    if (intensitySelected.contains(IntensityUnits.Forced)) {
        addIntensityToList(IntensityUnits.Forced, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                forcedColor = mutableStateOf( BrightGreen),
                forcedText = mutableStateOf("Added"),
                forcedClicked = mutableStateOf(true),

                staticHoldText = mutableStateOf("Forced selected"),
                staticHoldColor = mutableStateOf(Color.Red),
                staticHoldClickable = mutableStateOf(false),

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }

    } else {
        if (intensitySelected.contains(IntensityUnits.Positive)) {
            if (intensitySelected.contains(IntensityUnits.Static)) {
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = mutableStateOf("Static Selected"),
                        forcedColor = mutableStateOf(Color.Red),
                        forcedClicked = mutableStateOf(false),

                        listOfIntensityComponentName = listOfIntensityComponentName
                    )
                }
            } else {
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = mutableStateOf("Add"),
                        forcedColor = mutableStateOf(Green),
                        forcedClicked = mutableStateOf(false),

                        listOfIntensityComponentName = listOfIntensityComponentName
                    )
                }

            }
        } else {
            exerciseComponentUIState.update {
                it.copy(
                    forcedText = mutableStateOf("Need Positive"),
                    forcedColor = mutableStateOf(Color.Red),
                    forcedClicked = mutableStateOf(false),

                    listOfIntensityComponentName = listOfIntensityComponentName
                )
            }
        }
    }

    return exerciseComponentUIState.value
}








