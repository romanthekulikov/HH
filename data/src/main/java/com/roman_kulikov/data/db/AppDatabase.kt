package com.roman_kulikov.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.roman_kulikov.data.converters.LocalDateConverter
import com.roman_kulikov.data.converters.StringListConverter
import com.roman_kulikov.data.db.dao.VacancyDao
import com.roman_kulikov.data.db.models.EntityVacancy

@Database(
    entities = [EntityVacancy::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [LocalDateConverter::class, StringListConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao

}