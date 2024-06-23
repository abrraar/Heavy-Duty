package com.example.heavyduty.data.local.exercise

import androidx.room.Dao
import androidx.room.Query
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDAO {

    @Query("SELECT * FROM cycle")
    fun getExercise(): Flow<Cycle>

}