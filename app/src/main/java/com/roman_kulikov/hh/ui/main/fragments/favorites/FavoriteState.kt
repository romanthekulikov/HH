package com.roman_kulikov.hh.ui.main.fragments.favorites

import com.roman_kulikov.domain.entities.Vacancy

data class FavoriteState(
    val vacancyList: List<Vacancy> = emptyList()
)
