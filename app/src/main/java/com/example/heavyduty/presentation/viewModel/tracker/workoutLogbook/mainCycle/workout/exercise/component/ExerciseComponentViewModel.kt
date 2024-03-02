package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.units.IntensityUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ExerciseComponentViewModel
@Inject constructor(
    private val exerciseModel: ExerciseModel
) : ViewModel()
{
    private val _exerciseComponentUIState = MutableStateFlow(ExerciseComponentUIState())
    val exerciseComponentUIState = _exerciseComponentUIState.asStateFlow()
    private val listOfIntensityComponentName = mutableListOf<IntensityUnits>()

    init {
        updateState()
    }

    // Helper Functions
    private fun addIntensityToList(intensityUnits: IntensityUnits) {
        if (!listOfIntensityComponentName.contains(intensityUnits)){
            listOfIntensityComponentName.add(intensityUnits)
        }
    }
    private fun removeIntensityFromList(intensityUnits: IntensityUnits) {
        if(listOfIntensityComponentName.contains(intensityUnits)){
            listOfIntensityComponentName.remove(intensityUnits)
        }
    }

    // Updates the UI
    private fun updateState() {

        // Contains positive
        if (exerciseModel.value.containsKey(IntensityUnits.Positive))
        {
            addIntensityToList(IntensityUnits.Positive)
            _exerciseComponentUIState.update{
                it.copy(
                    positiveRepColor = BrightGreen,
                    positiveText = "Added",

                    listOfIntensityComponentName = listOfIntensityComponentName
                    )
            }
            // Forced depends on positive therefore it's state will be updated when positive is updated
            // but if static is present forced cannot be selected
            if(exerciseModel.value.containsKey(IntensityUnits.Static)){
                if(exerciseModel.value.containsKey(IntensityUnits.Forced) || !exerciseModel.value.containsKey(IntensityUnits.Forced)){
                    removeIntensityFromList(IntensityUnits.Forced)
                    _exerciseComponentUIState.update {
                        it.copy(
                            forcedText = "Static Selected",
                            forcedColor = Color.Red,
                            forcedClickable = false,
                        )
                    }
                }
            }
            // if static isn't available and positive is then forced is clickable
            else if(!exerciseModel.value.containsKey(IntensityUnits.Static)){
                if (exerciseModel.value.containsKey(IntensityUnits.Forced)){
                    addIntensityToList(IntensityUnits.Forced)
                    _exerciseComponentUIState.update {
                        it.copy(
                            forcedText = "Added",
                            forcedColor = BrightGreen,
                            forcedClickable = true,
                        )
                    }
                }
                else{
                    removeIntensityFromList(IntensityUnits.Forced)
                    _exerciseComponentUIState.update {
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
        else if (!exerciseModel.value.containsKey(IntensityUnits.Positive))
        {
            removeIntensityFromList(IntensityUnits.Positive)
            removeIntensityFromList(IntensityUnits.Forced)

            _exerciseComponentUIState.update {
                Log.i("Update","")
                it.copy(
                    positiveRepColor = Green,
                    positiveText = "Add",

                    forcedText = "Need Positive Reps",
                    forcedColor = Color.Red,
                    forcedClickable = false,

                    listOfIntensityComponentName = listOfIntensityComponentName
                )
            }

        }

        // Contain static
        if (exerciseModel.value.containsKey(IntensityUnits.Static))
        {
            addIntensityToList(IntensityUnits.Static)
            _exerciseComponentUIState.update {
                it.copy(
                    staticHoldColor = BrightGreen,
                    staticHoldText = "Added",

                    forcedClickable = false,
                    forcedColor = Color.Red,
                    forcedText = "Static Selected",

                    listOfIntensityComponentName = listOfIntensityComponentName
                )
            }

        }
        // Doesn't contain Static
        else
        {
            removeIntensityFromList(IntensityUnits.Static)
            _exerciseComponentUIState.update {
                it.copy(
                    staticHoldColor = Green,
                    staticHoldText = "Add",

                    listOfIntensityComponentName = listOfIntensityComponentName
                )
            }
            if (exerciseModel.value.containsKey(IntensityUnits.Positive))
            {
                if (exerciseModel.value.containsKey(IntensityUnits.Forced)){
                    addIntensityToList(IntensityUnits.Forced)
                    _exerciseComponentUIState.update {
                        it.copy(
                            forcedText = "Added",
                            forcedColor = BrightGreen,
                            forcedClickable = true,
                        )
                    }
                }
                else if(!exerciseModel.value.containsKey(IntensityUnits.Forced))
                {
                    removeIntensityFromList(IntensityUnits.Forced)
                    _exerciseComponentUIState.update {
                        it.copy(
                            forcedText = "Add",
                            forcedColor = Green,
                            forcedClickable = true,
                        )
                    }
                }
            }
            else if(!exerciseModel.value.containsKey(IntensityUnits.Positive)){
                removeIntensityFromList(IntensityUnits.Forced)
                _exerciseComponentUIState.update {
                    it.copy(
                        forcedText = "Need Positive",
                        forcedColor = Color.Red,
                        forcedClickable = false,
                    )
                }
            }

        }



    }

    fun onExerciseComponentEvent(events: ExerciseComponentEvents){
        when(events){
            is ExerciseComponentEvents.IntensityComponentClicked ->
               {
                   when(events.component)
                   {
                       IntensityUnits.Positive -> {
                               if (listOfIntensityComponentName.contains(IntensityUnits.Positive))
                               {
                                   removeIntensityFromList(IntensityUnits.Positive)
                                   removeIntensityFromList(IntensityUnits.Forced)
                                   _exerciseComponentUIState.update {
                                       it.copy(
                                           positiveRepColor = Green,
                                           positiveText = "Add",

                                           forcedText = "Need Positive",
                                           forcedColor = Color.Red,
                                           forcedClickable = false,

                                           listOfIntensityComponentName = listOfIntensityComponentName
                                           )
                                   }

                                   Log.i("list", listOfIntensityComponentName.toString())
                                   // Forced depends on positive therefore it's state will be updated when positive is updated
                                   // but if static is present forced cannot be selected
                                   if(listOfIntensityComponentName.contains(IntensityUnits.Static)){
                                       if(listOfIntensityComponentName.contains(IntensityUnits.Forced) || !listOfIntensityComponentName.contains(IntensityUnits.Forced)){
                                           removeIntensityFromList(IntensityUnits.Forced)
                                           _exerciseComponentUIState.update {
                                               it.copy(
                                                   forcedText = "Static Selected",
                                                   forcedColor = Color.Red,
                                                   forcedClickable = false,
                                               )
                                           }
                                       }
                                   }

                               }
                               // list does not have positive
                               else
                               {
                                   addIntensityToList(IntensityUnits.Positive)
                                   _exerciseComponentUIState.update {

                                       it.copy(
                                           positiveRepColor = BrightGreen,
                                           positiveText = "Added",

                                           listOfIntensityComponentName = listOfIntensityComponentName
                                       )
                                   }

                                   if(listOfIntensityComponentName.contains(IntensityUnits.Static)){
                                       if(listOfIntensityComponentName.contains(IntensityUnits.Forced) || !listOfIntensityComponentName.contains(IntensityUnits.Forced)){
                                           removeIntensityFromList(IntensityUnits.Forced)
                                           _exerciseComponentUIState.update {
                                               it.copy(
                                                   forcedText = "Static Selected",
                                                   forcedColor = Color.Red,
                                                   forcedClickable = false,
                                               )
                                           }
                                       }
                                   }
                                   // if static isn't available and positive is then forced is clickable
                                   else if(!listOfIntensityComponentName.contains(IntensityUnits.Static)){
                                       if (listOfIntensityComponentName.contains(IntensityUnits.Forced))
                                       {
                                           addIntensityToList(IntensityUnits.Forced)
                                           _exerciseComponentUIState.update {
                                               it.copy(
                                                   forcedText = "Added",
                                                   forcedColor = BrightGreen,
                                                   forcedClickable = true,
                                               )
                                           }
                                       }
                                       else if(!listOfIntensityComponentName.contains(IntensityUnits.Forced))
                                       {
                                           removeIntensityFromList(IntensityUnits.Forced)
                                           _exerciseComponentUIState.update {
                                               it.copy(
                                                   forcedText = "Add",
                                                   forcedColor = Green,
                                                   forcedClickable = true,
                                               )
                                           }
                                       }

                                   }
                                   Log.i("list", listOfIntensityComponentName.toString())
                               }
                           }

                       IntensityUnits.Static -> {
                           if (!listOfIntensityComponentName.contains(IntensityUnits.Static))
                           {
                               addIntensityToList(IntensityUnits.Static)
                               _exerciseComponentUIState.update {
                                   it.copy(
                                       staticHoldColor = BrightGreen,
                                       staticHoldText = "Added",

                                       listOfIntensityComponentName = listOfIntensityComponentName
                                   )
                               }
                               if(listOfIntensityComponentName.contains(IntensityUnits.Forced) || !listOfIntensityComponentName.contains(IntensityUnits.Forced)){
                                   removeIntensityFromList(IntensityUnits.Forced)
                                   _exerciseComponentUIState.update {
                                       it.copy(
                                           forcedText = "Static Selected",
                                           forcedColor = Color.Red,
                                           forcedClickable = false,
                                       )
                                   }
                               }

                           }
                           // Does not have Static
                           else
                           {
                               removeIntensityFromList(IntensityUnits.Static)
                               _exerciseComponentUIState.update {

                                   it.copy(
                                       staticHoldColor = Green,
                                       staticHoldText = "Add",

                                       listOfIntensityComponentName = listOfIntensityComponentName
                                   )
                               }
                               if (listOfIntensityComponentName.contains(IntensityUnits.Positive))
                               {
                                   if (listOfIntensityComponentName.contains(IntensityUnits.Forced)){
                                       addIntensityToList(IntensityUnits.Forced)
                                       _exerciseComponentUIState.update {
                                           it.copy(
                                               forcedText = "Added",
                                               forcedColor = BrightGreen,
                                               forcedClickable = true,
                                           )
                                       }
                                   }
                                   else if(!listOfIntensityComponentName.contains(IntensityUnits.Forced))
                                   {
                                       removeIntensityFromList(IntensityUnits.Forced)
                                       _exerciseComponentUIState.update {
                                           it.copy(
                                               forcedText = "Add",
                                               forcedColor = Green,
                                               forcedClickable = true,
                                           )
                                       }
                                   }
                               }
                               else if(!listOfIntensityComponentName.contains(IntensityUnits.Positive)){
                                   removeIntensityFromList(IntensityUnits.Forced)
                                   _exerciseComponentUIState.update {
                                       it.copy(
                                           forcedText = "Need Positive",
                                           forcedColor = Color.Red,
                                           forcedClickable = false,
                                       )
                                   }
                               }


                           }
                       }

                       IntensityUnits.Forced -> {}

                       IntensityUnits.Negative-> {}

                       IntensityUnits.PreExhaust-> {}

                   }
               }
        }
    }


}
