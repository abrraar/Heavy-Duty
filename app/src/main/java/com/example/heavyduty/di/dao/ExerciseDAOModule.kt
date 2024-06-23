package com.example.heavyduty.di.dao

import com.example.heavyduty.data.local.HeavyDutyDataBase
import com.example.heavyduty.data.local.exercise.ExerciseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ExerciseDAOModule {

    @Singleton
    @Provides
    fun providesExerciseDAO(heavyDutyDatabase: HeavyDutyDataBase): ExerciseDAO {
        return heavyDutyDatabase.exerciseDAO()
    }

}