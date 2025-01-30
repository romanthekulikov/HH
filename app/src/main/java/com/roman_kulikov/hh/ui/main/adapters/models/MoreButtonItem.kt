package com.roman_kulikov.hh.ui.main.adapters.models

import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.hh.ui.main.adapters.delegators.MoreButtonAdapterDelegate

data class MoreButtonItem(
    val vacancyNumber: Int,
    val onClickListener: MoreButtonAdapterDelegate.OnClickListener
) : DisplayableItem