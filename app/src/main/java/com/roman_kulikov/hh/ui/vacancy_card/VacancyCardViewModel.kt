package com.roman_kulikov.hh.ui.vacancy_card

import com.roman_kulikov.domain.Repository
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.ui.base.BaseViewModel
import com.roman_kulikov.tools.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class VacancyCardViewModel : BaseViewModel<VacancyCardState>() {
    @Inject
    lateinit var repository: Repository

    override val _state: MutableStateFlow<VacancyCardState> = MutableStateFlow(VacancyCardState())

    init {
        HHApp.appComponent.inject(this)
    }

    fun getVacancy(vacancyId: String) {
        launch {
            when(val result = repository.getVacancyById(vacancyId)) {
                is Result.Success -> _state.update { it.copy(vacancy = result.data) }
                is Result.Failure -> _state.update { it.copy(errorMessage = result.cause) }
                else -> { /* nothing */ }
            }
        }
    }
}