package com.roman_kulikov.data.api.models

import com.roman_kulikov.domain.entities.Offer
import com.squareup.moshi.Json

data class ApiOffer(
    @Json(name = "id")
    override val offerId: String?,

    @Json(name = "title")
    override val title: String,

    @Json(name = "button")
    override val button: ApiButton?,

    @Json(name = "link")
    override val link: String

) : Offer