package com.roman_kulikov.hh.di

import com.roman_kulikov.data.di.ApiModule
import com.roman_kulikov.data.di.BindModule
import com.roman_kulikov.data.di.DbModule
import com.roman_kulikov.hh.ui.main.MainViewModel
import com.roman_kulikov.hh.ui.main.fragments.favorites.FavoriteFragment
import com.roman_kulikov.hh.ui.main.fragments.search.SearchFragment
import com.roman_kulikov.hh.ui.vacancy_card.VacancyCardActivity
import com.roman_kulikov.hh.ui.vacancy_card.VacancyCardViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindModule::class, ApiModule::class, DbModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(searchFragment: SearchFragment)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(vacancyCardViewModel: VacancyCardViewModel)
    fun inject(vacancyCardActivity: VacancyCardActivity)
}