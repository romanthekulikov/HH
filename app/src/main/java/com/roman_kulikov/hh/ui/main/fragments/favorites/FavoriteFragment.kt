package com.roman_kulikov.hh.ui.main.fragments.favorites

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
import com.roman_kulikov.hh.databinding.FragmentFavoriteBinding
import com.roman_kulikov.hh.getVibrator
import com.roman_kulikov.hh.ui.main.MainViewModel
import com.roman_kulikov.hh.ui.main.adapters.SearchListAdapter
import com.roman_kulikov.hh.ui.main.adapters.delegators.VacancyAdapterDelegate
import com.roman_kulikov.hh.ui.vacancy_card.VacancyCardActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragment : Fragment(), VacancyAdapterDelegate.OnVacancyClickListener, VibrationServicesHelper {
    @Inject
    lateinit var searchListAdapter: SearchListAdapter

    private lateinit var binding: FragmentFavoriteBinding
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
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        initObservers()
        viewModel.getFavorites()

        return binding.root
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.favoriteState.collect { state ->
                setupRecyclerView(state.vacancyList)
            }
        }
    }

    private fun setupRecyclerView(vacanciesList: List<Vacancy>) {
        binding.titleStub.visibility = if (vacanciesList.isNotEmpty()) View.GONE else View.VISIBLE
        searchListAdapter.items = vacanciesList
        searchListAdapter.setOnVacancyClickListener(this)
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFavorites.adapter = searchListAdapter
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