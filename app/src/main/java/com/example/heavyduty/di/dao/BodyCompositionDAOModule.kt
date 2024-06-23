package com.example.heavyduty.di.dao

import com.example.heavyduty.data.local.HeavyDutyDataBase
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BodyCompositionDAOModule {
    @Singleton
    @Provides
    fun providesBodyCompositionDAO(heavyDutyDatabase: HeavyDutyDataBase): BodyCompositionDAO {
        return heavyDutyDatabase.bodyCompositionDAO()
    }
}