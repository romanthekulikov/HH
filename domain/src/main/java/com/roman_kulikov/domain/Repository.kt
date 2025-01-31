package com.roman_kulikov.domain

import com.roman_kulikov.domain.entities.Response
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.tools.Result

interface Repository {
    suspend fun getData(): Result<Response>
    suspend fun getVacancyById(vacancyId: String): Result<Vacancy>
    suspend fun saveVacancies(vacanciesList: List<Vacancy>): Result<Boolean>
    suspend fun removeFromFavorite(vacancy: Vacancy): Result<Boolean>
    suspend fun getFavoritesVacancies(): Result<List<Vacancy>>
}