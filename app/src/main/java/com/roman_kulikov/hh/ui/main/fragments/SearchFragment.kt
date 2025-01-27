package com.roman_kulikov.hh.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.roman_kulikov.domain.entities.DisplayableItem
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.databinding.FragmentSearchBinding
import com.roman_kulikov.hh.ui.main.MainViewModel
import com.roman_kulikov.hh.ui.main.adapters.SearchListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {
    init {
        HHApp.appComponent.inject(this)
    }

    @Inject
    lateinit var searchListAdapter: SearchListAdapter

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isAdded) {
            viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                val dataList = listOf<DisplayableItem>()
                // TODO: Display data
                if (state.vacancyList != null) {

                }
            }
        }
    }

    fun setupRecyclerView() {

    }
}