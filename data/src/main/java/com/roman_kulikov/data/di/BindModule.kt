package com.roman_kulikov.data.di

import com.roman_kulikov.data.VacanciesRepositoryImpl
import com.roman_kulikov.domain.VacanciesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BindModule {

    @Binds
    @Singleton
    fun bindVacancyRepository(vacanciesRepositoryImpl: VacanciesRepositoryImpl): VacanciesRepository
}