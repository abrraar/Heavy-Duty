package com.example.heavyduty.di.repository

import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogBookDAO
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WorkoutLogbookRepositoryModule {

    @Singleton
    @Provides
    fun providesWorkoutLogbookRepository(workoutLogBookDAO: WorkoutLogBookDAO): WorkoutLogbookOfflineRepository {
        return WorkoutLogbookOfflineRepository(workoutLogBookDAO)
    }

}