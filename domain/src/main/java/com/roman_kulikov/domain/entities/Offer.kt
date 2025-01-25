package com.roman_kulikov.domain.entities

interface Offer {
    val offerId: String?
    val title: String
    val button: Button?
    val link: String
}