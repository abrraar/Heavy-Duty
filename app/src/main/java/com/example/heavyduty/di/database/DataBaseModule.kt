package com.example.heavyduty.di.database

import android.content.Context
import androidx.room.Room
import com.example.heavyduty.data.local.Constants
import com.example.heavyduty.data.local.HeavyDutyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Singleton
    @Provides
    fun provideHeavyDutyDb(@ApplicationContext context: Context): HeavyDutyDataBase{
        return Room.databaseBuilder(
            context = context,
            klass = HeavyDutyDataBase::class.java,
            name = Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}