package com.roman_kulikov.hh.ui.main

import com.roman_kulikov.domain.entities.Offer
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.hh.ui.base.BaseState

data class MainState(
    val offerList: List<Offer>? = null,
    val vacancyList: List<Vacancy>? = null,
    val errorMessage: String? = null,
    val loadInProgress: Boolean = false
) : BaseState
