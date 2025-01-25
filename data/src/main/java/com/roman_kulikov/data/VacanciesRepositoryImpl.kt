package com.roman_kulikov.data

import com.roman_kulikov.data.api.ApiService
import com.roman_kulikov.domain.VacanciesRepository
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.tools.Result
import javax.inject.Inject

class VacanciesRepositoryImpl @Inject constructor(
    private val exceptionCatcher: ApiExceptionCatcher,
    private val apiService: ApiService
) : VacanciesRepository {

    override suspend fun getVacancies(): Result<List<Vacancy>> {
        return exceptionCatcher.launchWithCatch {
            val response = apiService.getData()

            Result.Success(response.vacanciesList)
        }
    }
}