package com.roman_kulikov.hh.ui.main.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.hh.ui.main.adapters.delegators.MoreButtonAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.OffersListAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.SearchFieldAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.VacancyAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.VacancyTitleAdapterDelegate
import javax.inject.Inject

class SearchListAdapter @Inject constructor(
    searchFieldAdapterDelegate: SearchFieldAdapterDelegate,
    offersListAdapterDelegate: OffersListAdapterDelegate,
    vacancyTitleAdapterDelegate: VacancyTitleAdapterDelegate,
    vacancyAdapterDelegate: VacancyAdapterDelegate,
    moreButtonAdapterDelegate: MoreButtonAdapterDelegate
) : ListDelegationAdapter<List<DisplayableItem>>(
    searchFieldAdapterDelegate,
    offersListAdapterDelegate,
    vacancyTitleAdapterDelegate,
    vacancyAdapterDelegate,
    moreButtonAdapterDelegate
) {
    fun changeSearchState(newDataList: List<DisplayableItem>) {
        setItems(newDataList)
        notifyDataSetChanged()
    }
}