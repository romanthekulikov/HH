package com.roman_kulikov.hh.ui.main.fragments.search

import com.roman_kulikov.domain.entities.Offer
import com.roman_kulikov.domain.entities.Vacancy

data class SearchDataState(
    val offerList: List<Offer> = emptyList(),
    val vacancyList: List<Vacancy> = emptyList()
)
