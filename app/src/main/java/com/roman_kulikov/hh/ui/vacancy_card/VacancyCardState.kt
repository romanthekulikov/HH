package com.roman_kulikov.hh.ui.vacancy_card

import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.hh.ui.base.BaseState

data class VacancyCardState(
    val vacancy: Vacancy? = null,
    val errorMessage: String? = null
) : BaseState
