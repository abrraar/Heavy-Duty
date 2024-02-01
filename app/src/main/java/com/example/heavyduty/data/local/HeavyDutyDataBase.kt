package com.example.heavyduty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionDAO
import com.example.heavyduty.domain.model.tracker.bodyComposition.BMI
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight

@Database(
    entities = [
        Weight::class,
        Height::class,
        BodyFat::class,
        MuscleMass::class,
        BMI::class],
    version = 1,
    exportSchema = false)
abstract class HeavyDutyDataBase: RoomDatabase()
{
    abstract fun bodyCompositionDAO(): BodyCompositionDAO

    companion object {
        const val DATABASE_NAME: String = "HeavyDuty_db"
        @Volatile
        private var Instance: HeavyDutyDataBase? = null
        fun getDatabase(context: Context): HeavyDutyDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context = context, klass =  HeavyDutyDataBase::class.java, name = DATABASE_NAME)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}