package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.Red
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.WorkoutUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentViewModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState
import com.example.heavyduty.units.IntensityUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutLogbookViewModel
@Inject constructor(
    private val workoutLogbookOfflineRepository: WorkoutLogbookOfflineRepository
): ViewModel()
{
    private val _workoutLogbookUIState = MutableStateFlow(WorkoutLogbookUIState())
    val workoutLogbookUIState = _workoutLogbookUIState.asStateFlow()

    var workoutLogbookComponentUIState by mutableStateOf(WorkoutLogbookComponentUIState())
        private set

    private var cycleList: List<Cycle> = listOf()

    private val _workoutUIState = MutableStateFlow(WorkoutUIState())
    val workoutUIState = _workoutUIState.asStateFlow()

    private val _exerciseScreenUIState = MutableStateFlow(ExerciseScreenUIState())
    val exerciseScreenUIState = _exerciseScreenUIState.asStateFlow()


    init {
        getCycle()
    }
    
    private fun getCycle(){
        viewModelScope.launch {
            workoutLogbookOfflineRepository.getAllCycle().collectLatest{
                it -> cycleList = it
               _workoutLogbookUIState.update { it.copy( listOfCycle = cycleList ) }
               _workoutUIState.update { it.copy( listOfCycle = cycleList ) }
               _exerciseScreenUIState.update { it.copy( listOfCycle = cycleList ) }
            }
        }
    }

    fun onEvents(events: WorkoutLogbookEvents) {
        when(events){
            is WorkoutLogbookEvents.CycleSelected -> {
                _workoutUIState.update {
                    it.copy(
                        cycleIndex = events.cycleIndex
                    )
                }
                _exerciseScreenUIState.update {
                    it.copy(
                        cycleIndex = events.cycleIndex
                    )
                }
            }

            is WorkoutLogbookEvents.WorkoutSelected ->
                _exerciseScreenUIState.update {
                    it.copy(
                        workoutIndex = events.workoutIndex
                    )
                }

        }
    }

    fun onCycleDelete()
    {

    }

    fun onIntensityComponentSelected(intensityUnit: IntensityUnits, cycleNumber: Int, workoutNumber: Int, exerciseModel: ExerciseModel)
    {
        val cycle = cycleList

        for ((index, exercise) in cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise.withIndex()){
            Log.i("exercise Model from selection", exerciseModel.exerciseName)
            Log.i("exercise Model from list", exercise.exerciseName)
            if(exerciseModel.exerciseNumber == exercise.exerciseNumber){

                Log.i("if statement is true", exercise.toString())
                when(intensityUnit){

                    IntensityUnits.Positive -> {
                        // Remove positive
                        if (exerciseModel.value.containsKey(IntensityUnits.Positive)) {
                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value.remove(IntensityUnits.Positive)
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }
                        }
                        // Add Positive
                        else {

                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value[IntensityUnits.Positive] = 0
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }

                        }
                    }

                    IntensityUnits.Static -> {
                        // Remove Static
                        if (exerciseModel.value.containsKey(IntensityUnits.Static)) {

                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value.remove(IntensityUnits.Static)
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }

                        }
                        // Add Static
                        else {
                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value[IntensityUnits.Static] = 0
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }
                        }
                    }

                    IntensityUnits.Negative -> {
                        // Remove Negative
                        if (exerciseModel.value.containsKey(IntensityUnits.Negative)) {

                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value.remove(IntensityUnits.Negative)
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }

                        }
                        // Add Negative
                        else {

                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value[IntensityUnits.Negative] = 0
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }

                        }
                    }

                    IntensityUnits.Forced -> {
                        // Remove Forced
                        if (exerciseModel.value.containsKey(IntensityUnits.Forced)) {

                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value.remove(IntensityUnits.Forced)
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }

                        }
                        // Add Forced
                        else {
                            cycle[cycleNumber].cycleModel.listOfWorkout[workoutNumber].listOfExercise[index].value[IntensityUnits.Forced] = 0
                            viewModelScope.launch {
                                workoutLogbookOfflineRepository.updateCycle(cycle[cycleNumber])
                            }

                        }
                    }

                    IntensityUnits.PreExhaust -> {}
                }
            }
            else{
                Log.i("if statmenet false", exercise.exerciseName)
            }
        }
    }



}