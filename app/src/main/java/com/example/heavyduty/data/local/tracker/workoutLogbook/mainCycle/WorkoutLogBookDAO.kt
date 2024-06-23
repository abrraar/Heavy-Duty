package com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogBookDAO {

//------------------- Cycle ----------------------
    @Update
    suspend fun updateCycle(cycle: Cycle)

    @Delete
    suspend fun deleteCycle(cycle: Cycle)

    @Query("SELECT * FROM cycle")
    fun getAllCycle(): Flow<List<Cycle>>

    @Query("SELECT * FROM cycle WHERE cycle_id = :cycleID")
    fun getCycleByID(cycleID: Int): Flow<Cycle>

    @Query("SELECT COUNT(*) FROM cycle")
    fun getCycleCount(): Flow<Int>


}