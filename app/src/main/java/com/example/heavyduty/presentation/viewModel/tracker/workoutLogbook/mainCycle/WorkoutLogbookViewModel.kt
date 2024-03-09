package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.WorkoutUIState
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
    private val _cycleUIState = MutableStateFlow(CycleUIState())
    val cycleUIState = _cycleUIState.asStateFlow()

    var cycleComponentUIState by mutableStateOf(CycleComponentUIState())
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
               _cycleUIState.update { it.copy( listOfCycle = cycleList ) }
               _workoutUIState.update { it.copy( listOfCycle = cycleList ) }
               _exerciseScreenUIState.update { it.copy( listOfCycle = cycleList ) }
            }
        }
    }

    fun onWorkoutLogBookEvents(events: WorkoutLogbookEvents) {
        when(events){
            // Workout and Exercise Screens
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
            // Exercise Screen
            is WorkoutLogbookEvents.WorkoutSelected -> {
                _exerciseScreenUIState.update {
                    it.copy(
                        workoutIndex = events.workoutIndex
                    )
                }
            }
            // Cycle Screen
            is WorkoutLogbookEvents.DeleteCycleClicked -> {
                if (events.status == "PROMPT"){
                    _cycleUIState.update {
                        it.copy(
                            deleteState = true
                        )
                    }
                }
                if(events.status == "CONFIRM"){
                    _cycleUIState.update {
                        it.copy(
                            deleteState = false
                        )
                    }
                    onCycleDelete(events.cycle)
                }
                if(events.status == "CANCELLED"){
                    _cycleUIState.update {
                        it.copy(
                            deleteState = false
                        )
                    }
                }

            }
            // Exercise Screen
            is WorkoutLogbookEvents.AddIntensityComponent -> {
                var repsInInt = 0
                repsInInt = if(events.reps == "" || events.reps == "0"){ 0 } else { events.reps.toInt() }

                Log.i("repsInInt", repsInInt.toString())
                val cycle = cycleList
                for ((index, exercise) in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.withIndex()){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        when(events.intensityUnit){

                            IntensityUnits.Positive -> {

                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Positive) || events.exerciseModel.value.containsKey(IntensityUnits.Positive)) {
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Positive] = repsInInt
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.Static -> {
                                // Remove Static
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Static) || events.exerciseModel.value.containsKey(IntensityUnits.Static)) {
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Static]  = repsInInt
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }

                                }

                            }

                            IntensityUnits.Negative -> {
                                // Remove Negative
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Negative) || events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Negative]  = repsInInt
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.Forced -> {
                                // Remove Forced
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Forced) ||  events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Forced]  = repsInInt
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.PreExhaust -> {}
                        }
                    }
                }
            }
            // Exercise Screen
            is WorkoutLogbookEvents.DeleteIntensityComponent -> {
                val cycle = cycleList
                for ((index, exercise) in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.withIndex()){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        when(events.intensityUnit){

                            IntensityUnits.Positive -> {
                                if (events.exerciseModel.value.containsKey(IntensityUnits.Positive)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value.remove(
                                        IntensityUnits.Positive
                                    )
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }

                                }
                            }

                            IntensityUnits.Static -> {

                                if (events.exerciseModel.value.containsKey(IntensityUnits.Static)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value.remove(IntensityUnits.Static)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }

                                }

                            }

                            IntensityUnits.Negative -> {

                                if (events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value.remove(IntensityUnits.Negative)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }

                                }

                            }

                            IntensityUnits.Forced -> {
                                // Remove Forced
                                if (events.exerciseModel.value.containsKey(IntensityUnits.Forced)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value.remove(IntensityUnits.Forced)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }

                                }

                            }

                            IntensityUnits.PreExhaust -> {}
                        }
                    }
                }
            }

        }
    }

    private fun onCycleDelete(cycle: Cycle)
    {
        viewModelScope.launch {
            workoutLogbookOfflineRepository.deleteCycle(cycle)
        }
        getCycle()
    }


}