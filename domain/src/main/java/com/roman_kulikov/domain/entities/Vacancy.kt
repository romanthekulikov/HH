package com.roman_kulikov.domain.entities

import java.time.LocalDate

interface Vacancy {
    val id: String
    val lookingNumber: Int?
    val title: String
    val address: Address
    val company: String
    val experience: Experience
    val publishedDate: LocalDate
    val isFavorite: Boolean
    val salary: Salary
    val schedules: List<String>
    val appliedNumber: Int?
    val description: String?
    val responsibilities: String
    val questions: List<String>
}