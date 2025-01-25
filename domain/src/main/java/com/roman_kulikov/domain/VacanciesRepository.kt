package com.roman_kulikov.domain

import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.tools.Result

interface VacanciesRepository {
    suspend fun getVacancies(): Result<List<Vacancy>>
}