package com.roman_kulikov.data

import com.roman_kulikov.data.api.ApiService
import com.roman_kulikov.domain.Repository
import com.roman_kulikov.domain.entities.Response
import com.roman_kulikov.tools.Result
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val exceptionCatcher: ApiExceptionCatcher,
    private val apiService: ApiService
) : Repository {

    override suspend fun getData(): Result<Response> {
        return exceptionCatcher.launchWithCatch {
            Result.Success(apiService.getData())
        }
    }
}