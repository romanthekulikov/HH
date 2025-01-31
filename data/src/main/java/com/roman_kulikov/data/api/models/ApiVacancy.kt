package com.roman_kulikov.data.api.models

import com.roman_kulikov.domain.entities.Vacancy
import com.squareup.moshi.Json
import java.time.LocalDate

data class ApiVacancy(
    @Json(name = "id")
    override val id: String,

    @Json(name = "lookingNumber")
    override val lookingNumber: Int?,

    @Json(name = "title")
    override val title: String,

    @Json(name = "address")
    override val address: ApiAddress,

    @Json(name = "company")
    override val company: String,

    @Json(name = "experience")
    override val experience: ApiExperience,

    @Json(name = "publishedDate")
    override val publishedDate: LocalDate,

    @Json(name = "isFavorite")
    override var isFavorite: Boolean,

    @Json(name = "salary")
    override val salary: ApiSalary,

    @Json(name = "schedules")
    override val schedules: List<String>,

    @Json(name = "appliedNumber")
    override val appliedNumber: Int?,

    @Json(name = "description")
    override val description: String?,

    @Json(name = "responsibilities")
    override val responsibilities: String,

    @Json(name = "questions")
    override val questions: List<String>

) : Vacancy
