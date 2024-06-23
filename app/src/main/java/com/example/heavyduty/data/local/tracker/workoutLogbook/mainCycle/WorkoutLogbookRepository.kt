package com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import kotlinx.coroutines.flow.Flow

interface WorkoutLogbookRepository {
    suspend fun updateCycle(cycle: Cycle)
    suspend fun deleteCycle(cycle: Cycle)
    fun getAllCycle(): Flow<List<Cycle>>
    fun getCycleByID(cycleID: Int): Flow<Cycle>
    fun getCycleCount(): Flow<Int>

}