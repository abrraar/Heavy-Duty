package com.example.heavyduty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.heavyduty.data.local.Constants.DATABASE_NAME
import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionDAO
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionDAO
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.AddCycleDAO
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogBookDAO
import com.example.heavyduty.data.local.exercise.ExerciseDAO
import com.example.heavyduty.domain.model.tracker.bodyComposition.BMI
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle

@Database(
    entities = [
        // Workout Logbook
        Cycle::class,
        // Body Composition
        Weight::class,
        Height::class,
        BodyFat::class,
        MuscleMass::class,
        BMI::class
               ],
    version = 1,
    exportSchema = false)
@TypeConverters(Converter::class)
abstract class HeavyDutyDataBase: RoomDatabase()
{
    abstract fun bodyCompositionDAO(): BodyCompositionDAO
    abstract fun addBodyCompositionDAO(): AddBodyCompositionDAO
    abstract fun exerciseDAO(): ExerciseDAO
    abstract fun addCycleDAO(): AddCycleDAO
    abstract fun workoutLogbookDAO(): WorkoutLogBookDAO

    companion object {
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