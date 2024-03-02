package com.example.heavyduty.di.dao

import com.example.heavyduty.data.local.HeavyDutyDataBase
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogBookDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WorkoutLogbookDAOModule {
    @Singleton
    @Provides
    fun providesWorkoutLogbookDAO(heavyDutyDatabase: HeavyDutyDataBase): WorkoutLogBookDAO {
        return heavyDutyDatabase.workoutLogbookDAO()
    }

}