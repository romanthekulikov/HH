package com.roman_kulikov.domain.entities

import com.roman_kulikov.domain.DisplayableItem

interface Offer : DisplayableItem {
    val offerId: String?
    val title: String
    val button: Button?
    val link: String
}