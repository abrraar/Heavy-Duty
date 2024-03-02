package com.example.heavyduty.data.local


import androidx.room.TypeConverter
import com.example.heavyduty.domain.model.tracker.workoutLogbook.CycleModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

// Extension function
inline fun <reified T > Gson.fromJson(json: String) =
    this.fromJson<T>(json, object: TypeToken<T>(){}.type)

open class Converter
{

    @TypeConverter
    fun toDate(date: Long?): Date? {
        return date?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }


    @TypeConverter
    fun cycleModelToPreference(cycleModel: CycleModel): String {
        return Gson().toJson(cycleModel)
    }

    @TypeConverter
    fun preferenceToCycleModel(jasonString: String): CycleModel
    {
       return Gson().fromJson(jasonString)
    }
}
