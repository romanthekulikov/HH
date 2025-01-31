package com.roman_kulikov.data.converters

import com.roman_kulikov.data.db.models.EntityAddress
import com.roman_kulikov.data.db.models.EntityExperience
import com.roman_kulikov.data.db.models.EntitySalary
import com.roman_kulikov.data.db.models.EntityVacancy
import com.roman_kulikov.domain.entities.Address
import com.roman_kulikov.domain.entities.Experience
import com.roman_kulikov.domain.entities.Salary
import com.roman_kulikov.domain.entities.Vacancy

fun Vacancy.toEntity(): EntityVacancy {
    return EntityVacancy(
        id,
        lookingNumber,
        title,
        address.toEntity(),
        company,
        experience.toEntity(),
        publishedDate,
        isFavorite,
        salary.toEntity(),
        schedules,
        appliedNumber,
        description,
        responsibilities,
        questions
    )
}

fun Address.toEntity(): EntityAddress = EntityAddress(town, street, house)

fun Experience.toEntity() = EntityExperience(previewText, text)

fun Salary.toEntity() = EntitySalary(full, short)