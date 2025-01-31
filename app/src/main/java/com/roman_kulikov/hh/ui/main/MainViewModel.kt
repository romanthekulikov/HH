package com.roman_kulikov.hh.ui.main

import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.Repository
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.ui.base.BaseViewModel
import com.roman_kulikov.hh.ui.main.adapters.items.MoreButtonItem
import com.roman_kulikov.hh.ui.main.adapters.items.OffersListItem
import com.roman_kulikov.hh.ui.main.adapters.items.SearchViewItem
import com.roman_kulikov.hh.ui.main.adapters.items.VacancyTitle
import com.roman_kulikov.hh.ui.main.fragments.favorites.FavoriteState
import com.roman_kulikov.hh.ui.main.fragments.search.SearchFieldState
import com.roman_kulikov.hh.ui.main.fragments.search.SearchDataState
import com.roman_kulikov.tools.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : BaseViewModel<MainState>() {
    @Inject
    lateinit var repository: Repository

    override val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())

    private val _searchDataState: MutableStateFlow<SearchDataState> = MutableStateFlow(SearchDataState())
    val searchDataState: StateFlow<SearchDataState>
        get() = _searchDataState.asStateFlow()

    private val _searchFieldState: MutableStateFlow<SearchFieldState> = MutableStateFlow(SearchFieldState())
    val searchFieldState: StateFlow<SearchFieldState>
        get() = _searchFieldState.asStateFlow()

    private val _favoriteState: MutableStateFlow<FavoriteState> = MutableStateFlow(FavoriteState())
    val favoriteState: StateFlow<FavoriteState>
        get() = _favoriteState.asStateFlow()

    init {
        HHApp.appComponent.inject(this)
    }

    fun getData() {
        if (_searchDataState.value.vacancyList.isNotEmpty()) return
        updateData()
    }

    fun getFavorites() {
        launchWithProgress {
            when (val result = repository.getFavoritesVacancies()) {
                is Result.Failure -> _state.update { it.copy(errorMessage = result.cause) }
                is Result.Success -> _favoriteState.update { it.copy(vacancyList = result.data) }
                else -> { /* nothing */ }
            }
        }
    }

    private fun updateData() {
        launchWithProgress {
            when (val result = repository.getData()) {
                is Result.Success -> {
                    _searchDataState.update {
                        it.copy(
                            offerList = result.data.offersList,
                            vacancyList = result.data.vacanciesList
                        )
                    }
                    saveVacanciesList(result.data.vacanciesList.filter { it.isFavorite })
                    val favoriteCount = result.data.vacanciesList.count { it.isFavorite }
                    _state.update { it.copy(favoriteCount = favoriteCount) }
                }

                is Result.Failure -> _state.update { it.copy(errorMessage = result.cause) }

                else -> { /* nothing */ }
            }
        }
    }

    private fun saveVacanciesList(vacanciesList: List<Vacancy>) {
        launch {
            val result = repository.saveVacancies(vacanciesList)
            if (result is Result.Failure) {
                _state.update { it.copy(errorMessage = result.cause) }
            }
        }
    }

    fun deleteErrorMessage() {
        _state.update { it.copy(errorMessage = null) }
    }

    fun getFullModeItems(): List<DisplayableItem> {
        _searchFieldState.update { it.copy(isFullMode = true) }
        val itemsList: MutableList<DisplayableItem> = mutableListOf(
            SearchViewItem(_searchFieldState.value.text, true, _searchDataState.value.vacancyList.size),
            OffersListItem(searchDataState.value.offerList),
            VacancyTitle
        )
        itemsList.addAll(searchDataState.value.vacancyList)

        return itemsList
    }

    fun getLimitedModeItems(): List<DisplayableItem> {
        _searchFieldState.update { it.copy(isFullMode = false) }
        val itemsList: MutableList<DisplayableItem> = mutableListOf(
            SearchViewItem(_searchFieldState.value.text, false, _searchDataState.value.vacancyList.size),
            OffersListItem(_searchDataState.value.offerList),
            VacancyTitle
        )
        if (_searchDataState.value.vacancyList.size > 2) {
            itemsList.addAll(_searchDataState.value.vacancyList.subList(0, 3))
        } else {
            itemsList.addAll(_searchDataState.value.vacancyList)
        }

        val moreButtonItem = MoreButtonItem(_searchDataState.value.vacancyList.size)
        itemsList.add(moreButtonItem)

        return itemsList
    }

    fun saveVacancy(vacancy: Vacancy, reverseEvent: () -> Unit) {
        launch {
            when(val result = repository.saveVacancies(listOf(vacancy))) {
                is Result.Success -> {
                    if (vacancy.isFavorite) {
                        _state.update { it.copy(favoriteCount = state.value.favoriteCount + 1) }
                    } else {
                        _state.update { it.copy(favoriteCount = state.value.favoriteCount - 1) }
                    }
                }
                is Result.Failure -> {
                    _state.update { it.copy(errorMessage = result.cause) }
                    reverseEvent()
                }
                else -> { /* nothing */ }
            }
        }
    }

    fun setSearchFieldText(text: String) {
        _searchFieldState.update { it.copy(text = text) }
    }

    override fun onStartJob() {
        _state.update { it.copy(loadInProgress = true) }
    }

    override fun onFinishJob() {
        _state.update { it.copy(loadInProgress = false) }
    }

}