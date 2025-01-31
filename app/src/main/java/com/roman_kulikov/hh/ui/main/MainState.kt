package com.roman_kulikov.hh.ui.main

import com.roman_kulikov.hh.ui.base.BaseState

data class MainState(
    val errorMessage: String? = null,
    val loadInProgress: Boolean = false,
    val favoriteCount: Int = 0
) : BaseState
