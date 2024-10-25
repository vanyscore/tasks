package com.vanyscore.app.room.converters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toDate(time: Long): Date {
        val date = Date(time)
        return date
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}