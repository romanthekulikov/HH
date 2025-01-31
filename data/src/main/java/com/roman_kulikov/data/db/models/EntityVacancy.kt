package com.roman_kulikov.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.roman_kulikov.domain.entities.Vacancy
import java.time.LocalDate

@Entity(tableName = "vacancy")
data class EntityVacancy(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: String,

    @ColumnInfo(name = "looking_number")
    override val lookingNumber: Int?,

    @ColumnInfo(name = "title")
    override val title: String,

    @Embedded
    override val address: EntityAddress,

    @ColumnInfo(name = "company")
    override val company: String,

    @Embedded
    override val experience: EntityExperience,

    @ColumnInfo(name = "published_date")
    override val publishedDate: LocalDate,

    @ColumnInfo(name = "is_favorite")
    override var isFavorite: Boolean,

    @Embedded
    override val salary: EntitySalary,

    @ColumnInfo(name = "schedules")
    override val schedules: List<String>,

    @ColumnInfo(name = "applied_number")
    override val appliedNumber: Int?,

    @ColumnInfo(name = "description")
    override val description: String?,

    @ColumnInfo(name = "responsibilities")
    override val responsibilities: String,

    @ColumnInfo(name = "questions")
    override val questions: List<String>

) : Vacancy