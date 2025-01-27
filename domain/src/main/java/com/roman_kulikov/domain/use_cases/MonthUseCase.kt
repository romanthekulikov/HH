package com.roman_kulikov.domain.use_cases

import javax.inject.Inject

class MonthUseCase @Inject constructor() {
    operator fun invoke(monthNumber: Int): String {
        return when (monthNumber) {
            1 -> "января"
            2 -> "февраля"
            3 -> "марта"
            4 -> "апреля"
            5 -> "мая"
            6 -> "июня"
            7 -> "июля"
            8 -> "августа"
            9 -> "сентября"
            10 -> "октября"
            11 -> "ноября"
            else -> "декабря"
        }
    }
}