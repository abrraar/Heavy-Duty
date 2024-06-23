package com.example.heavyduty.data.local.exercise

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseOfflineRepository
@Inject constructor(
    private val exerciseDAO: ExerciseDAO
): ExerciseRepository {

    override fun getLastExercise(): Flow<Cycle> = exerciseDAO.getExercise()

}