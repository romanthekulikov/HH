package com.roman_kulikov.data.api.models

import com.roman_kulikov.domain.entities.Address
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class ApiAddress(
    @Json(name = "town")
    override val town: String,

    @Json(name = "street")
    override val street: String,

    @Json(name = "house")
    override val house: String

) : Address
