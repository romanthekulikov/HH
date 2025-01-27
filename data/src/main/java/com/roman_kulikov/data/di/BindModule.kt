package com.roman_kulikov.data.di

import com.roman_kulikov.data.RepositoryImpl
import com.roman_kulikov.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BindModule {

    @Binds
    @Singleton
    fun bindVacancyRepository(vacanciesRepositoryImpl: RepositoryImpl): Repository
}