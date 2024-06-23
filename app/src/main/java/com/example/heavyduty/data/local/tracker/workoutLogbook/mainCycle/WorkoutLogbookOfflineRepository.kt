package com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import kotlinx.coroutines.flow.Flow
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

    override fun getCycleByID(cycleID: Int): Flow<Cycle> = workoutLogBookDAO.getCycleByID(cycleID)

    override fun getCycleCount(): Flow<Int> = workoutLogBookDAO.getCycleCount()


}