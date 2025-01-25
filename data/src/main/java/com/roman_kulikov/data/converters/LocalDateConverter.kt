package com.roman_kulikov.data.converters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate

class LocalDateConverter {
    @FromJson
    fun fromJson(dateString: String): LocalDate {
        if (dateString.length > 10) {
            return LocalDate.parse(dateString.substring(0, 10))
        }
        return LocalDate.now()
    }

    @ToJson
    fun toJson(localDate: LocalDate): String {
        return localDate.toString()
    }
}