package com.roman_kulikov.hh.ui.main.fragments.search

import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.VibrationServicesHelper
import com.roman_kulikov.hh.databinding.FragmentSearchBinding
import com.roman_kulikov.hh.getVibrator
import com.roman_kulikov.hh.ui.main.MainViewModel
import com.roman_kulikov.hh.ui.main.adapters.SearchListAdapter
import com.roman_kulikov.hh.ui.main.adapters.delegators.MoreButtonAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.SearchFieldAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.delegators.VacancyAdapterDelegate
import com.roman_kulikov.hh.ui.vacancy_card.VacancyCardActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment(), MoreButtonAdapterDelegate.OnMoreButtonClickListener, SearchFieldAdapterDelegate.OnClickListener,
    VacancyAdapterDelegate.OnVacancyClickListener, VibrationServicesHelper {
    @Inject
    lateinit var searchListAdapter: SearchListAdapter

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainViewModel
    override val vibrator: Vibrator? by lazy { context?.let { getVibrator(it) } }

    init {
        HHApp.appComponent.inject(this)
    }

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
            viewModel.searchDataState.collectLatest { state ->
                if (state.vacancyList.isNotEmpty()) {
                    setupRecyclerView()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        if (viewModel.searchFieldState.value.isFullMode) {
            setFullMode()
        } else {
            setLimitedMode()
        }

        searchListAdapter.setOnVacancyClickListener(this)
        searchListAdapter.setSearchOnClickListener(this)
        searchListAdapter.setMoreButtonClickListener(this)

        binding.recyclerViewSearch.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSearch.adapter = searchListAdapter
    }

    override fun onClick() {
        setFullMode()
    }

    override fun onBackClick() {
        setLimitedMode()
    }

    override fun onEnterClick() {
        viewModel.setSearchFieldText("")
        setFullMode()
    }

    override fun setSearchText(text: String) {
        viewModel.setSearchFieldText(text)
    }

    private fun setFullMode() {
        val itemsList = viewModel.getFullModeItems()
        searchListAdapter.changeSearchState(itemsList)
    }

    private fun setLimitedMode() {
        val itemsList = viewModel.getLimitedModeItems()
        searchListAdapter.changeSearchState(itemsList)
    }

    override fun saveVacancy(vacancy: Vacancy, reverseEvent: () -> Unit) {
        withVibration {
            viewModel.saveVacancy(vacancy, reverseEvent)
        }
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        val intent = activity?.let { VacancyCardActivity.createIntent(it, vacancy.id) }
        startActivity(intent)
    }

}