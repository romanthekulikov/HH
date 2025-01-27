package com.roman_kulikov.hh.ui.main

import com.roman_kulikov.domain.Repository
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.ui.base.BaseViewModel
import com.roman_kulikov.tools.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : BaseViewModel<MainState>() {
    init {
        HHApp.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository
    override val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())

    fun getData() {
        launchWithProgress {
            when (val result = repository.getData()) {
                is Result.Success -> _state.update {
                    it.copy(
                        offerList = result.data.offersList,
                        vacancyList = result.data.vacanciesList
                    )
                }

                is Result.Failure -> _state.update { it.copy(errorMessage = result.cause) }
                else -> { /* nothing */ }
            }
        }
    }

    override fun onStartJob() {
        _state.update { it.copy(loadInProgress = true) }
    }

    override fun onFinishJob() {
        _state.update { it.copy(loadInProgress = false) }
    }

}