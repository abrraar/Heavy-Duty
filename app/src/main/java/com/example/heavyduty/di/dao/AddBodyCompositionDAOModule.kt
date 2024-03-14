package com.example.heavyduty.di.dao

import com.example.heavyduty.data.local.HeavyDutyDataBase
import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AddBodyCompositionDAOModule {
    @Singleton
    @Provides
    fun providesAddBodyCompositionDAO(heavyDutyDatabase: HeavyDutyDataBase): AddBodyCompositionDAO {
        return heavyDutyDatabase.addBodyCompositionDAO()
    }
}