package com.roman_kulikov.hh.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.roman_kulikov.hh.R
import com.roman_kulikov.hh.databinding.ActivityMainBinding
import com.roman_kulikov.hh.ui.base.BaseActivity
import com.roman_kulikov.hh.ui.main.custom_view.BottomNavigationItem
import com.roman_kulikov.hh.ui.main.custom_view.HHBottomNavigationView
import com.roman_kulikov.hh.ui.main.fragments.FavoriteFragment
import com.roman_kulikov.hh.ui.main.fragments.MessagesFragment
import com.roman_kulikov.hh.ui.main.fragments.ProfileFragment
import com.roman_kulikov.hh.ui.main.fragments.ResponseFragment
import com.roman_kulikov.hh.ui.main.fragments.SearchFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<MainState>(), HHBottomNavigationView.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModels()

    private val itemToFragmentMap = mapOf(
        BottomNavigationItem.SEARCH to SearchFragment(),
        BottomNavigationItem.FAVORITES to FavoriteFragment(),
        BottomNavigationItem.RESPONSE to ResponseFragment(),
        BottomNavigationItem.MESSAGES to MessagesFragment(),
        BottomNavigationItem.PROFILE to ProfileFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.state.value.vacancyList == null) {
            requestData()
        }

        installBottomNavigation()
        observeState()
    }

    private fun installBottomNavigation() {
        binding.HHBottomNavigationView.setHandler(this)
        itemToFragmentMap[BottomNavigationItem.SEARCH]?.let { replaceFragmentTo(it) }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                binding.progressBar.isActivated = state.loadInProgress
                binding.progressBar.visibility = if (state.loadInProgress) View.VISIBLE else View.GONE

                if (state.errorMessage != null) {
                    showToast(state.errorMessage)
                }

            }
        }
    }

    private fun requestData() {
        binding.progressBar.isActivated = true
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getData()
    }

    private fun replaceFragmentTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    override fun onSelectItem(item: BottomNavigationItem) {
        itemToFragmentMap[item]?.let { replaceFragmentTo(it) }
    }
}