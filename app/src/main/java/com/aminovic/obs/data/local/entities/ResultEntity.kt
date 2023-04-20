package com.aminovic.obs.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.aminovic.obs.data.local.Constants.Tables.RESULTS_TABLE

@Entity(tableName = RESULTS_TABLE)
data class ResultEntity(
    @ColumnInfo(name = "city")
    val city: String? = null,

    @ColumnInfo(name = "year")
    val year: Int? = null,

    @ColumnInfo(name = "gold")
    val gold: Int? = null,

    @ColumnInfo(name = "silver")
    val silver: Int? = null,

    @ColumnInfo(name = "bronze")
    val bronze: Int? = null,

    @ColumnInfo(name = "results")
    val results: ResultEntity? = null,
)
