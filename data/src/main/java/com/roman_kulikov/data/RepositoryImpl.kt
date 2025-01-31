package com.roman_kulikov.data

import com.roman_kulikov.data.api.ApiService
import com.roman_kulikov.data.catchers.ApiExceptionCatcher
import com.roman_kulikov.data.catchers.DbExceptionCatcher
import com.roman_kulikov.data.converters.toEntity
import com.roman_kulikov.data.db.AppDatabase
import com.roman_kulikov.domain.Repository
import com.roman_kulikov.domain.entities.Response
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.tools.Result
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiExceptionCatcher: ApiExceptionCatcher,
    private val dbExceptionCatcher: DbExceptionCatcher,
    private val apiService: ApiService,
    private val db: AppDatabase
) : Repository {

    override suspend fun getData(): Result<Response> {
        return apiExceptionCatcher.launchWithCatch {
            when (val favoriteVacancies = getFavoritesVacancies()) {
                is Result.Failure -> return@launchWithCatch Result.Failure(favoriteVacancies.cause)
                is Result.NoNetwork -> Result.NoNetwork()
                is Result.Success -> {
                    val data = apiService.getData()
                    data.checkingFavoriteVacancies(favoriteVacancies.data)
                    saveVacancies(data.vacanciesList)
                    Result.Success(data)
                }
            }
        }
    }

    override suspend fun getVacancyById(vacancyId: String): Result<Vacancy> {
        return dbExceptionCatcher.launchWithCatch {
            val vacancy = db.vacancyDao().getVacancyById(vacancyId)
            Result.Success(vacancy)
        }
    }

    override suspend fun saveVacancies(vacanciesList: List<Vacancy>): Result<Boolean> {
        return dbExceptionCatcher.launchWithCatch {
            db.vacancyDao().addVacancies(vacanciesList.map { it.toEntity() })
            Result.Success(true)
        }
    }

    override suspend fun removeFromFavorite(vacancy: Vacancy): Result<Boolean> {
        return dbExceptionCatcher.launchWithCatch {
            db.vacancyDao().deleteVacancy(vacancy.toEntity())
            Result.Success(true)
        }
    }

    override suspend fun getFavoritesVacancies(): Result<List<Vacancy>> {
        return dbExceptionCatcher.launchWithCatch {
            val favorites = db.vacancyDao().getFavorite()
            Result.Success(favorites)
        }
    }

    private fun Response.checkingFavoriteVacancies(checkedList: List<Vacancy>) {
        checkedList.forEach { favoriteVacancy ->
            this.vacanciesList.find { it.id == favoriteVacancy.id }?.let {
                it.isFavorite = true
            }
        }
    }
}