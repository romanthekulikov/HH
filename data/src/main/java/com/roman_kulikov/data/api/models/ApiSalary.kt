package com.roman_kulikov.data.api.models

import com.roman_kulikov.domain.entities.Salary
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class ApiSalary(
    @Json(name = "full")
    override val full: String,

    @Json(name = "short")
    override val short: String?

) : Salary
