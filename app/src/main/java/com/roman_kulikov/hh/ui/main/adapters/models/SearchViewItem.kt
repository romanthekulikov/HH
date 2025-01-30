package com.roman_kulikov.hh.ui.main.adapters.models

import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.hh.ui.main.adapters.delegators.SearchFieldAdapterDelegate

data class SearchViewItem(
    val fullMode: Boolean,
    val vacancyNumber: Int = 0,
    val onBackClickListener: SearchFieldAdapterDelegate.OnClickListener
) : DisplayableItem