package com.roman_kulikov.domain.entities

interface Response {
    val offersList: List<Offer>
    val vacanciesList: List<Vacancy>
}