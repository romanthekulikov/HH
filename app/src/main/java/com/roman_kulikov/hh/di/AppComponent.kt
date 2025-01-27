package com.roman_kulikov.hh.di

import com.roman_kulikov.data.di.ApiModule
import com.roman_kulikov.data.di.BindModule
import com.roman_kulikov.hh.ui.main.MainViewModel
import com.roman_kulikov.hh.ui.main.adapters.OffersListAdapterDelegate
import com.roman_kulikov.hh.ui.main.adapters.VacancyAdapterDelegate
import com.roman_kulikov.hh.ui.main.fragments.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindModule::class, ApiModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(mainViewModel: VacancyAdapterDelegate)
    fun inject(offersListAdapterDelegate: OffersListAdapterDelegate)
    fun inject(searchFragment: SearchFragment)
}