package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component

import android.util.Log
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
    val positiveText: String = "Added",
    val positiveClicked: Boolean = false,
    val positivePreviousReps: String = "",

    val staticHoldColor: Color = Green,
    val staticHoldText: String = "Add",
    val staticHoldClickable: Boolean = true,
    val staticClicked: Boolean = false,
    val staticPreviousReps: String = "",

    val negativeColor: Color = Green,
    val negativeText: String = "Add",
    val negativeClicked: Boolean = false,
    val negativePreviousReps: String = "",

    val forcedColor: Color = Green,
    val forcedText: String = "Add",
    val forcedClickable: Boolean = true,
    val forcedClicked: Boolean = false,
    val forcedPreviousReps: String = "",

    val preExhaustColor: Color = Green,
    val preExhaustText: String = "Add",
    val preExhaustClickable: Boolean = true,
    val preExhaustClicked: Boolean = false,
    val preExhaustPreviousReps: String = "",

    val weightDifference: String = "",
    val positiveDifference: String = "",
    val staticDifference: String = "",
    val negativeDifference: String = "",
    val forcedDifference: String = "",

    val totalDifference: String = ""

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

//------------------------------- Previous Weight, Weight Difference and total Difference ------------------------------------

    exerciseComponentUIState.update {
        it.copy(
            previousWeight = previousWeight.toString(),
            weightDifference = "${weightDifference}%",
            totalDifference = "${totalDifference}%"
        )
    }
//------------------------------- Positive --------------------------------------
    exerciseComponentUIState.update {

        it.copy(
            positivePreviousReps = previousReps[IntensityUnits.Positive].toString(),
            positiveDifference = "${ intensityDifference[IntensityUnits.Positive] }%"
        )
    }
    if (intensitySelected.contains(IntensityUnits.Positive)) {
        addIntensityToList(IntensityUnits.Positive, listOfIntensityComponentName)
        val posRep = value[IntensityUnits.Positive].toString()
        Log.i("pos rep", posRep)
        exerciseComponentUIState.update {
            it.copy(
                positiveRepColor = (BrightGreen) ,
                positiveText = "Added",
                positiveClicked = false,
                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        // Forced depends on positive therefore it's state will be updated when positive is updated
        // but if static is present forced cannot be selected
        if (intensitySelected.contains(IntensityUnits.Static)) {
            exerciseComponentUIState.update {
                it.copy(
                    forcedText = "Static Selected",
                    forcedColor = Color.Red,
                    forcedClickable = false,
                )
            }
        }
        // if static isn't available and positive is then forced is clickable
        else {
            if (intensitySelected.contains(IntensityUnits.Forced)) {
                addIntensityToList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = "Added",
                        forcedColor = BrightGreen,
                        forcedClickable = true,
                        forcedClicked = true
                    )
                }
            } else {
                removeIntensityFromList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = "Add",
                        forcedColor = Green,
                        forcedClickable = true,
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
                positiveText = "Add",
                positiveClicked = true,

                forcedText = "Need Positive Reps",
                forcedColor = Color.Red,
                forcedClickable = true,

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }

    }

//-------------------------------Contain static-------------------------------------------
    exerciseComponentUIState.update {
        it.copy(
            staticPreviousReps = previousReps[IntensityUnits.Static].toString(),
            staticDifference = "${ intensityDifference[IntensityUnits.Static] }%"
        )
    }
    if (intensitySelected.contains(IntensityUnits.Static)) {
        addIntensityToList(IntensityUnits.Static, listOfIntensityComponentName)

        exerciseComponentUIState.update {
            it.copy(
                staticHoldColor = BrightGreen,
                staticHoldText = "Added",
                staticClicked = true,

                forcedClickable = true,
                forcedColor = Color.Red,
                forcedText = "Static Selected",

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
    }
    // Doesn't contain Static
    else {
        removeIntensityFromList(IntensityUnits.Static, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                staticHoldColor = Green,
                staticHoldText = "Add",
                staticClicked = false,

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        if (intensitySelected.contains(IntensityUnits.Positive)) {
            if (intensitySelected.contains(IntensityUnits.Forced)) {
                addIntensityToList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = "Added",
                        forcedColor = BrightGreen,
                        forcedClicked = true,
                        forcedClickable = true,
                    )
                }
            } else if (!intensitySelected.contains(IntensityUnits.Forced)) {
                removeIntensityFromList(IntensityUnits.Forced, listOfIntensityComponentName)
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = "Add",
                        forcedColor = Green,
                        forcedClickable = true,
                    )
                }
            }
        } else if (!intensitySelected.contains(IntensityUnits.Positive)) {
            removeIntensityFromList(IntensityUnits.Forced, listOfIntensityComponentName)
            exerciseComponentUIState.update {
                it.copy(
                    forcedText = "Need Positive Reps",
                    forcedColor = Color.Red,
                    forcedClickable = true,
                )
            }
        }

    }

//--------------------------------Negative-------------------------------------
    exerciseComponentUIState.update {
        it.copy(
            negativePreviousReps = previousReps[IntensityUnits.Negative].toString(),
            negativeDifference = "${ intensityDifference[IntensityUnits.Negative] }%"
        )
    }
    if (intensitySelected.contains(IntensityUnits.Negative)) {
        addIntensityToList(IntensityUnits.Negative, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                negativeColor = BrightGreen,
                negativeText = "Added",
                negativeClicked = true,

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        Log.i(" yes negative", listOfIntensityComponentName.toString())
    } else {
        removeIntensityFromList(IntensityUnits.Negative, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                negativeText = "Add",
                negativeColor = Green,
                negativeClicked = false,

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }
        Log.i(" no negative", listOfIntensityComponentName.toString())
    }

//-------------------------Forced-------------------------------------
    exerciseComponentUIState.update {
        it.copy(
            forcedPreviousReps = previousReps[IntensityUnits.Forced].toString(),
            forcedDifference = "${ intensityDifference[IntensityUnits.Forced] }%"
        )
    }
    if (intensitySelected.contains(IntensityUnits.Forced)) {
        addIntensityToList(IntensityUnits.Forced, listOfIntensityComponentName)
        exerciseComponentUIState.update {
            it.copy(
                forcedColor = BrightGreen,
                forcedText = "Added",
                forcedClicked = true,

                staticHoldText = "Forced selected",
                staticHoldColor = Color.Red,
                staticHoldClickable = false,

                listOfIntensityComponentName = listOfIntensityComponentName
            )
        }

    } else {
        if (intensitySelected.contains(IntensityUnits.Positive)) {
            if (intensitySelected.contains(IntensityUnits.Static)) {
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = "Static Selected",
                        forcedColor = Color.Red,
                        forcedClicked = false,

                        listOfIntensityComponentName = listOfIntensityComponentName
                    )
                }
            } else {
                exerciseComponentUIState.update {
                    it.copy(
                        forcedText = "Add",
                        forcedColor = Green,
                        forcedClicked = false,

                        listOfIntensityComponentName = listOfIntensityComponentName
                    )
                }

            }
        } else {
            exerciseComponentUIState.update {
                it.copy(
                    forcedText = "Need Positive Reps",
                    forcedColor = Color.Red,
                    forcedClicked = false,
                    listOfIntensityComponentName = listOfIntensityComponentName
                )
            }
        }
    }

    return exerciseComponentUIState.value
}








