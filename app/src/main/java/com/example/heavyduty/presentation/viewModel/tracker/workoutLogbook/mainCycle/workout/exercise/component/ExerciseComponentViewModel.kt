package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.units.IntensityUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseComponentViewModel
@Inject constructor(
    private val workoutLogbookOfflineRepository: WorkoutLogbookOfflineRepository
) : ViewModel()
{
    private var _exerciseComponentUIState: MutableStateFlow<ExerciseComponentUIState> = MutableStateFlow(ExerciseComponentUIState())
    fun getExerciseModel(exerciseModel: ExerciseModel) {
        _exerciseComponentUIState = MutableStateFlow(exerciseModel.toExerciseComponentUIState())
    }
    val exerciseComponentUIState = _exerciseComponentUIState.asStateFlow()

    private val listOfIntensityComponentName = mutableStateListOf(IntensityUnits.Positive)
    private var exerciseList: ArrayList<ExerciseModel> = arrayListOf()

    private var cycleList: List<Cycle> = listOf()

    init {
        getCycle()
    }


    private fun getCycle(){
        viewModelScope.launch {
            workoutLogbookOfflineRepository.getAllCycle().collectLatest{
                cycleList = it
            }
        }
    }

    // Helper Functions
    private fun addIntensityToList(intensityUnits: IntensityUnits, listOfIntensityComponentName: MutableList<IntensityUnits>) {
        if (!listOfIntensityComponentName.contains(intensityUnits)){
            listOfIntensityComponentName.add(intensityUnits)
        }
    }
    private fun removeIntensityFromList(intensityUnits: IntensityUnits, listOfIntensityComponentName: MutableList<IntensityUnits>) {
        if(listOfIntensityComponentName.contains(intensityUnits)){
            listOfIntensityComponentName.remove(intensityUnits)
        }
    }

    private fun updatePositive(){
        var cycleList: List<Cycle> = listOf()
        viewModelScope.launch {
            workoutLogbookOfflineRepository.getAllCycle().collectLatest{
                cycle ->
                cycleList = cycle
            }
        }

    }

    // Get UI state
//    private fun updateState() {
//        viewModelScope.launch {
//            workoutLogbookOfflineRepository.getIndex(1).collectLatest {
//                index ->
//                Log.i("index", index.toString())
//                workoutLogbookOfflineRepository.getAllCycle().collectLatest {
//                    exerciseList = it[index.cycleIndex].cycleModel.listOfWorkout[index.workoutIndex].listOfExercise
//                    for (exerciseModel in exerciseList){
//                        // Contains positive
//                        if (exerciseModel.value.containsKey(IntensityUnits.Positive))
//                        {
//                            addIntensityToList(IntensityUnits.Positive, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update{
//                                it.copy(
//                                    positiveRepColor = BrightGreen,
//                                    positiveText = "Added",
//                                    positiveClicked = false,
//                                    positiveNum = mutableStateOf(exerciseModel.value[IntensityUnits.Positive].toString()) ,
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                            // Forced depends on positive therefore it's state will be updated when positive is updated
//                            // but if static is present forced cannot be selected
//                            if(exerciseModel.value.containsKey(IntensityUnits.Static)){
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Static Selected",
//                                        forcedColor = Color.Red,
//                                        forcedClickable = false,
//                                    )
//                                }
//                            }
//                            // if static isn't available and positive is then forced is clickable
//                            else
//                            {
//                                if (exerciseModel.value.containsKey(IntensityUnits.Forced)){
//                                    addIntensityToList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                                    _exerciseComponentUIState.update {
//                                        it.copy(
//                                            forcedText = "Added",
//                                            forcedColor = BrightGreen,
//                                            forcedClickable = true,
//                                        )
//                                    }
//                                }
//                                else{
//                                    removeIntensityFromList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                                    _exerciseComponentUIState.update {
//                                        it.copy(
//                                            forcedText = "Add",
//                                            forcedColor = Green,
//                                            forcedClickable = true,
//                                        )
//                                    }
//                                }
//                            }
//                        }
//
//                        // Does not contain positive
//                        else if (!exerciseModel.value.containsKey(IntensityUnits.Positive) )
//                        {
//                            removeIntensityFromList(IntensityUnits.Positive, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            removeIntensityFromList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//
//                            _exerciseComponentUIState.update {
//                                Log.i("Update","")
//                                it.copy(
//                                    positiveRepColor = Green,
//                                    positiveText = "Add",
//                                    positiveClicked = true,
//
//                                    forcedText = "Need Positive Reps",
//                                    forcedColor = Color.Red,
//                                    forcedClickable = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//
//                        }
//
//                        // Contain static
//                        if (exerciseModel.value.containsKey(IntensityUnits.Static))
//                        {
//                            addIntensityToList(IntensityUnits.Static, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    staticHoldColor = BrightGreen,
//                                    staticHoldText = "Added",
//                                    staticClicked = true,
//                                    staticNum = mutableStateOf(exerciseModel.value[IntensityUnits.Static].toString()),
//
//                                    forcedClickable = false,
//                                    forcedColor = Color.Red,
//                                    forcedText = "Static Selected",
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//
//                        }
//                        // Doesn't contain Static
//                        else
//                        {
//                            removeIntensityFromList(IntensityUnits.Static, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    staticHoldColor = Green,
//                                    staticHoldText = "Add",
//                                    staticClicked = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                            if (exerciseModel.value.containsKey(IntensityUnits.Positive))
//                            {
//                                if (exerciseModel.value.containsKey(IntensityUnits.Forced)){
//                                    addIntensityToList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                                    _exerciseComponentUIState.update {
//                                        it.copy(
//                                            forceNum = mutableStateOf(exerciseModel.value[IntensityUnits.Forced].toString()),
//                                            forcedText = "Added",
//                                            forcedColor = BrightGreen,
//                                            forcedClickable = true,
//                                        )
//                                    }
//                                }
//                                else if(!exerciseModel.value.containsKey(IntensityUnits.Forced))
//                                {
//                                    removeIntensityFromList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                                    _exerciseComponentUIState.update {
//                                        it.copy(
//                                            forcedText = "Add",
//                                            forcedColor = Green,
//                                            forcedClickable = true,
//                                        )
//                                    }
//                                }
//                            }
//                            else if(!exerciseModel.value.containsKey(IntensityUnits.Positive)){
//                                removeIntensityFromList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Need Positive",
//                                        forcedColor = Color.Red,
//                                        forcedClickable = false,
//                                    )
//                                }
//                            }
//
//                        }
//
//// Negative
//                        if(exerciseModel.value.containsKey(IntensityUnits.Negative)){
//                            addIntensityToList(IntensityUnits.Negative, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    negativeColor = BrightGreen,
//                                    negativeText = "Added",
//                                    negativeClicked = true,
//                                    negativeNum = mutableStateOf(exerciseModel.value[IntensityUnits.Negative].toString()),
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                            Log.i(" yes negative", listOfIntensityComponentName.toString())
//                        }
//                        else
//                        {
//                            removeIntensityFromList(IntensityUnits.Negative, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    negativeText = "Add",
//                                    negativeColor = Green,
//                                    negativeClicked = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                            Log.i(" no negative", listOfIntensityComponentName.toString())
//                        }
//// Forced
//
//                        if(exerciseModel.value.containsKey(IntensityUnits.Forced)){
//                            addIntensityToList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    forceNum = mutableStateOf(exerciseModel.value[IntensityUnits.Forced].toString()),
//                                    forcedColor = BrightGreen,
//                                    forcedText = "Added",
//                                    forcedClicked = true,
//
//                                    staticHoldText = "Forced selected",
//                                    staticHoldColor = Color.Red,
//                                    staticHoldClickable = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//
//                        }
//                        else
//                        {
//                            if(exerciseModel.value.containsKey(IntensityUnits.Positive)){
//                                removeIntensityFromList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                                if (exerciseModel.value.containsKey(IntensityUnits.Static)){
//                                    _exerciseComponentUIState.update {
//                                        it.copy(
//                                            forcedText = "Static Selected",
//                                            forcedColor = Color.Red,
//                                            forcedClicked = false,
//
//                                            listOfIntensityComponentName = listOfIntensityComponentName
//                                        )
//                                    }
//                                }
//                                else
//                                {
//                                    _exerciseComponentUIState.update {
//                                        it.copy(
//                                            forcedText = "Add",
//                                            forcedColor = Green,
//                                            forcedClicked = true,
//
//                                            listOfIntensityComponentName = listOfIntensityComponentName
//                                        )
//                                    }
//
//                                }
//                            }
//                            else
//                            {
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Need Positive",
//                                        forcedColor = Color.Red,
//                                        forcedClicked = false,
//
//                                        listOfIntensityComponentName = listOfIntensityComponentName
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    /**
     * Exercise Components events
     * */
//    fun onExerciseComponentEvent(events: ExerciseComponentEvents){
//        when(events){
//            is ExerciseComponentEvents.DeleteExerciseClicked -> {
//                when(events.clicked){
//                    true -> {
//                        _exerciseComponentUIState.update {
//                            it.copy(
//                                deleteExercisePrompt = true
//                            )
//                        }
//                    }
//                    false -> {
//                        _exerciseComponentUIState.update {
//                            it.copy(
//                                deleteExercisePrompt = false
//                            )
//                        }
//                    }
//                }
//            }
//            is ExerciseComponentEvents.IntensityComponentClicked -> {
//                when(events.component)
//                {
//                    IntensityUnits.Positive -> {
//                        if (_exerciseComponentUIState.value.listOfIntensityComponentName.contains(IntensityUnits.Positive))
//                        {
//                            removeIntensityFromList(IntensityUnits.Positive, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            removeIntensityFromList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    positiveRepColor = Green,
//                                    positiveText = "Add",
//                                    positiveClicked = mutableStateOf(true),
//
////                                    forcedText = "Need Positive",
////                                    forcedColor = Color.Red,
////                                    forcedClickable = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }

//                            Log.i("list", listOfIntensityComponentName.toString())
//                            // Forced depends on positive therefore it's state will be updated when positive is updated
//                            // but if static is present forced cannot be selected
//                            if(listOfIntensityComponentName.contains(IntensityUnits.Static)){
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Static Selected",
//                                        forcedColor = Color.Red,
//                                        forcedClickable = false,
//                                    )
//                                }
//                            }

//                            if(listOfIntensityComponentName.contains(IntensityUnits.Static) && !listOfIntensityComponentName.contains(IntensityUnits.Forced)){
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        staticHoldColor = BrightGreen,
//                                        staticHoldClickable = true,
//                                        staticHoldText = "Added",
//                                        staticClicked = true
//                                    )
//                                }
//                            }
//                            else{
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        staticHoldColor = Green,
//                                        staticHoldClickable = true,
//                                        staticHoldText = "Add",
//                                        staticClicked = false
//                                    )
//                                }
//                            }


                        }
                        // list does not have positive
//                        else
//                        {
//                            addIntensityToList(IntensityUnits.Positive, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//
//                                it.copy(
//                                    positiveRepColor = BrightGreen,
//                                    positiveText = "Added",
//                                    positiveClicked = mutableStateOf(false),
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//

//                            if(listOfIntensityComponentName.contains(IntensityUnits.Static)){
//
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Static Selected",
//                                        forcedColor = Color.Red,
//                                        forcedClickable = false,
//
//                                        listOfIntensityComponentName = listOfIntensityComponentName
//                                    )
//                                }
//
//                            }
//                            // if static isn't available and positive is then forced is clickable
//                            else {
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Add",
//                                        forcedColor = Green,
//                                        forcedClickable = true,
//
//                                        listOfIntensityComponentName = listOfIntensityComponentName
//                                    )
//                                }
//
//                            }
//                        }
//                    }

//                    IntensityUnits.Static -> {
//                        if (!listOfIntensityComponentName.contains(IntensityUnits.Static))
//                        {
//
//                            addIntensityToList(IntensityUnits.Static, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    staticHoldColor = BrightGreen,
//                                    staticHoldText = "Added",
//                                    staticClicked = true,
//
//                                    forcedText = "Static Selected",
//                                    forcedColor = Color.Red,
//                                    forcedClickable = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//
//                        }
//                        else
//                        {
//                            removeIntensityFromList(IntensityUnits.Static, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//
//                                it.copy(
//                                    staticHoldColor = Green,
//                                    staticHoldText = "Add",
//                                    staticClicked = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                            if (listOfIntensityComponentName.contains(IntensityUnits.Positive))
//                            {
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Add",
//                                        forcedColor = Green,
//                                        forcedClickable = true,
//                                    )
//                                }
//                            }
//                            else
//                            {
//                                _exerciseComponentUIState.update {
//                                    it.copy(
//                                        forcedText = "Need Positive",
//                                        forcedColor = Color.Red,
//                                        forcedClickable = false,
//                                    )
//                                }
//                            }
//                        }
//                    }
//
//                    IntensityUnits.Forced -> {
//                        if (!listOfIntensityComponentName.contains(IntensityUnits.Forced))
//                        {
//                            addIntensityToList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    forcedColor = BrightGreen,
//                                    forcedText = "Added",
//                                    forcedClicked = true,
//
//                                    staticHoldColor = Color.Red,
//                                    staticHoldText = "Forced selected",
//                                    staticHoldClickable = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                        }
//                        else
//                        {
//                            removeIntensityFromList(IntensityUnits.Forced, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    staticHoldColor = Green,
//                                    staticHoldText = "Add",
//                                    staticHoldClickable = true,
//
//                                    forcedText = "Add",
//                                    forcedColor = Green,
//                                    forcedClicked = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                        }
//                    }
//
//                    IntensityUnits.Negative-> {
//                        if(!listOfIntensityComponentName.contains(IntensityUnits.Negative)){
//                            addIntensityToList(IntensityUnits.Negative, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    negativeColor = BrightGreen,
//                                    negativeText = "Added",
//                                    negativeClicked = true,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                        }
//                        else
//                        {
//                            removeIntensityFromList(IntensityUnits.Negative, _exerciseComponentUIState.value.listOfIntensityComponentName)
//                            _exerciseComponentUIState.update {
//                                it.copy(
//                                    negativeText = "Add",
//                                    negativeColor = Green,
//                                    negativeClicked = false,
//
//                                    listOfIntensityComponentName = listOfIntensityComponentName
//                                )
//                            }
//                        }
//                    }

//                    IntensityUnits.PreExhaust-> {}
//
//                    else -> {}
//
//                }
//            }
//            is ExerciseComponentEvents.DeleteExercise -> {
//                val cycle = cycleList
//                for ( exercise in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise){
//                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
//                        cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.remove(events.exerciseModel)
//                        break
//                    }
//                }
//                viewModelScope.launch {
//                    workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
//                }
//            }
//            else -> {}
//        }
//    }


    /**
     * Only valid inputs
     * */
    fun inputRestriction(intensityUnits: IntensityUnits, number: String): String
    {
        val limit = 2
        val staticLimit = 3
        var emptyValue = ""

        if (number == "null"|| number == "0" || number == "."){emptyValue = ""}

        Log.i("Number", number)


        // Check the size and delete if greater
        if(emptyValue.length > limit && intensityUnits != IntensityUnits.Static){
            for(num in number){
                emptyValue = number.substring( 0, number.length -1)
            }
        }


        if(emptyValue.length > staticLimit && intensityUnits == IntensityUnits.Static){
            for(num in number){
                emptyValue = number.substring( 0, number.length -1)
            }
        }


        return emptyValue
    }
