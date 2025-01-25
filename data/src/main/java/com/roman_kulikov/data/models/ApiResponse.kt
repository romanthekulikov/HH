package com.roman_kulikov.data.models

import com.roman_kulikov.domain.entities.Response
import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name = "offers")
    override val offersList: List<ApiOffer>,

    @Json(name = "vacancies")
    override val vacanciesList: List<ApiVacancy>

) : Response
