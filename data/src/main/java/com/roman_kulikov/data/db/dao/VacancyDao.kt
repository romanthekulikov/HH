package com.roman_kulikov.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.roman_kulikov.data.db.models.EntityVacancy

@Dao
interface VacancyDao {
    @Query("SELECT * FROM vacancy WHERE is_favorite = 1")
    fun getFavorite(): List<EntityVacancy>

    @Query("SELECT * FROM vacancy WHERE id = :vacancyId")
    fun getVacancyById(vacancyId: String): EntityVacancy

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVacancies(vacanciesList: List<EntityVacancy>)

    @Delete
    fun deleteVacancy(vacancy: EntityVacancy)
}