package com.roman_kulikov.hh.ui.main.adapters.items

import com.roman_kulikov.domain.DisplayableItem

data class SearchViewItem(
    var text: String,
    val fullMode: Boolean,
    val vacancyNumber: Int = 0
) : DisplayableItem