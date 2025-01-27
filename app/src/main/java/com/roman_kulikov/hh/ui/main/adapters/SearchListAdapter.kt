package com.roman_kulikov.hh.ui.main.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.roman_kulikov.domain.entities.DisplayableItem
import javax.inject.Inject

class SearchListAdapter @Inject constructor(
    offersListAdapterDelegate: OffersListAdapterDelegate,
    vacancyAdapterDelegate: VacancyAdapterDelegate
) : ListDelegationAdapter<List<DisplayableItem>>(
    offersListAdapterDelegate,
    vacancyAdapterDelegate
)