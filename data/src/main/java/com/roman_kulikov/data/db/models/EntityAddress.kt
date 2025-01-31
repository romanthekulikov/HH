package com.roman_kulikov.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.roman_kulikov.domain.entities.Address

@Entity(tableName = "address")
data class EntityAddress(
    @ColumnInfo(name = "town")
    override val town: String,

    @ColumnInfo(name = "street")
    override val street: String,

    @ColumnInfo(name = "house")
    override val house: String

) : Address
