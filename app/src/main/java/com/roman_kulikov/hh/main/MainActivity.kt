package com.roman_kulikov.hh.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.roman_kulikov.hh.base.BaseActivity
import com.roman_kulikov.hh.bottom_navigation_view.BottomNavigationItem
import com.roman_kulikov.hh.bottom_navigation_view.HHBottomNavigationView
import com.roman_kulikov.hh.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(), HHBottomNavigationView.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.HHBottomNavigationView.setHandler(this)
        lifecycleScope.launch {
            viewModel.state.collectLatest {

            }
        }

    }

    override fun onSelectItem(item: BottomNavigationItem) {
        TODO("Not yet implemented")
    }
}