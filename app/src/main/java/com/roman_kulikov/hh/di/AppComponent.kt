package com.roman_kulikov.hh.di

import com.roman_kulikov.data.di.ApiModule
import com.roman_kulikov.data.di.BindModule
import com.roman_kulikov.hh.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindModule::class, ApiModule::class])
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
}