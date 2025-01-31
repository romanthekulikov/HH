package com.roman_kulikov.hh.ui.main.adapters.items

import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.entities.Offer

data class OffersListItem(
    val offersList: List<Offer>
) : DisplayableItem
