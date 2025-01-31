package com.roman_kulikov.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.roman_kulikov.domain.entities.Salary

@Entity(tableName = "salary")
data class EntitySalary(
    @ColumnInfo(name = "full")
    override val full: String,

    @ColumnInfo(name = "short")
    override val short: String?

) : Salary