package com.roman_kulikov.hh.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.databinding.FragmentSearchBinding
import com.roman_kulikov.hh.ui.main.MainState
import com.roman_kulikov.hh.ui.main.MainViewModel
import com.roman_kulikov.hh.ui.main.adapters.SearchListAdapter
import com.roman_kulikov.hh.ui.main.adapters.delegators.MoreButtonAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.SearchFieldAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.VacancyAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.models.MoreButtonItem
import com.roman_kulikov.hh.ui.main.adapters.models.OffersListItem
import com.roman_kulikov.hh.ui.main.adapters.models.SearchViewItem
import com.roman_kulikov.hh.ui.main.adapters.models.VacancyTitle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment(), MoreButtonAdapterDelegate.OnClickListener, SearchFieldAdapterDelegate.OnClickListener,
    VacancyAdapterDelegate.OnItemClickListener {
    init {
        HHApp.appComponent.inject(this)
    }

    @Inject
    lateinit var searchListAdapter: SearchListAdapter

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isAdded) {
            super.onCreate(savedInstanceState)
            viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        observeState()

        return binding.root
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                if (state.vacancyList != null) {
                    setupRecyclerView(state)
                }
            }
        }
    }

    private fun setupRecyclerView(state: MainState) {
        disableFullMode(state)
        binding.recyclerViewSearch.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSearch.adapter = searchListAdapter
    }

    override fun onClick() {
        setFullMode(viewModel.state.value)
    }

    override fun onBackClick() {
        disableFullMode(viewModel.state.value)
    }

    private fun setFullMode(state: MainState) {
        val dataList: MutableList<DisplayableItem> = mutableListOf(
            SearchViewItem(true, onBackClickListener = this),
            OffersListItem(state.offerList ?: emptyList()),
            VacancyTitle("Вакансии для вас")
        )
        dataList.addAll(state.vacancyList ?: emptyList())
        searchListAdapter.changeSearchState(dataList)
    }

    private fun disableFullMode(state: MainState) {
        val dataList: MutableList<DisplayableItem> = mutableListOf(
            SearchViewItem(false, state.vacancyList?.size ?: 0, this),
            OffersListItem(state.offerList ?: emptyList()),
            VacancyTitle("Вакансии для вас")
        )
        dataList.addAll(state.vacancyList?.subList(0, 3) ?: emptyList())
        val moreButtonItem = state.vacancyList?.size?.let { MoreButtonItem(it, this) }
        moreButtonItem?.let { dataList.add(it) }
        searchListAdapter.changeSearchState(dataList)
    }

    override fun addVacancyToFavorite(vacancy: Vacancy, position: Int) {

    }

}