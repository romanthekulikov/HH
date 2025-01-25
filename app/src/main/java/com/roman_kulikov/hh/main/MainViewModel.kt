package com.roman_kulikov.hh.main

import com.roman_kulikov.domain.VacanciesRepository
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : BaseViewModel<MainState>() {
    init {
        HHApp.appComponent.inject(this)
    }

    @Inject
    lateinit var vacanciesRepository: VacanciesRepository
    override val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())

    fun getVacancies() {
        launch {
            vacanciesRepository.getVacancies()
        }
    }

    fun changeName() {
        _state.update { it.copy(name = if (_state.value.name == "Markus") "Roman" else "Markus") }
    }


}