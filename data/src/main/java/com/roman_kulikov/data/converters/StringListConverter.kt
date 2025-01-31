package com.roman_kulikov.data.converters

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringListConverter {
    @TypeConverter
    fun listToString(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun stringToList(jsonString: String): List<String> {
        return Json.decodeFromString(jsonString)
    }
}