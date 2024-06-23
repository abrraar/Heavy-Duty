package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle

@Dao
interface AddCycleDAO {

    @Insert
    suspend fun insertCycle(cycle: Cycle)

    @Update
    suspend fun updateCycle(cycle: Cycle)

    @Delete
    suspend fun deleteCycle(cycle: Cycle)
}