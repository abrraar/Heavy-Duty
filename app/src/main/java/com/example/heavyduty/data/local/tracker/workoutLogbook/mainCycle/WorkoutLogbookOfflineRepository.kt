package com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle

import android.util.Log
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class WorkoutLogbookOfflineRepository
@Inject constructor(
    private val workoutLogBookDAO: WorkoutLogBookDAO
)
    : WorkoutLogbookRepository
{
    override suspend fun updateCycle(cycle: Cycle) = workoutLogBookDAO.updateCycle(cycle)

    override suspend fun deleteCycle(cycle: Cycle) = workoutLogBookDAO.deleteCycle(cycle)

    override fun getAllCycle(): Flow<List<Cycle>> = workoutLogBookDAO.getAllCycle()

    suspend fun getExerciseList(workoutIndex: Int, cycleIndex: Int): ArrayList<ExerciseModel>{
        lateinit var exerciseList: ArrayList<ExerciseModel>
        workoutLogBookDAO.getAllCycle().collectLatest {
            exerciseList = it[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise
        }
        Log.i("exercise list from repos",exerciseList.toString())
        return exerciseList
    }
}