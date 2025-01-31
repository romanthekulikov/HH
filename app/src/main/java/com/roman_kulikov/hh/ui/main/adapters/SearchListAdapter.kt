package com.roman_kulikov.hh.ui.main.adapters

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.hh.ui.main.adapters.delegators.MoreButtonAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.OffersListAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.SearchFieldAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.VacancyAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.VacancyTitleAdapterDelegate
import javax.inject.Inject

class SearchListAdapter @Inject constructor(
    private val searchFieldAdapterDelegate: SearchFieldAdapterDelegate,
    offersListAdapterDelegate: OffersListAdapterDelegate,
    vacancyTitleAdapterDelegate: VacancyTitleAdapterDelegate,
    private val vacancyAdapterDelegate: VacancyAdapterDelegate,
    private val moreButtonAdapterDelegate: MoreButtonAdapterDelegate
) : ListDelegationAdapter<List<DisplayableItem>>(
    searchFieldAdapterDelegate,
    offersListAdapterDelegate,
    vacancyTitleAdapterDelegate,
    vacancyAdapterDelegate,
    moreButtonAdapterDelegate
) {
    @SuppressLint("NotifyDataSetChanged")
    fun changeSearchState(newDataList: List<DisplayableItem>) {
        setItems(newDataList)
        notifyDataSetChanged()
    }

    fun setOnVacancyClickListener(onVacancyClickListener: VacancyAdapterDelegate.OnVacancyClickListener) {
        vacancyAdapterDelegate.onVacancyClickListener = onVacancyClickListener
    }

    fun setSearchOnClickListener(onClickListener: SearchFieldAdapterDelegate.OnClickListener) {
        searchFieldAdapterDelegate.onClickListener = onClickListener
    }

    fun setMoreButtonClickListener(onMoreButtonClickListener: MoreButtonAdapterDelegate.OnMoreButtonClickListener) {
        moreButtonAdapterDelegate.onMoreButtonClickListener = onMoreButtonClickListener
    }
}