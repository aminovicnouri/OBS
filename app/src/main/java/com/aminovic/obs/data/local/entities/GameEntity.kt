package com.aminovic.obs.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.aminovic.obs.data.local.Constants.Tables.GAMES_TABLE

@Entity(tableName = GAMES_TABLE)
data class GameEntity(
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "athletes")
    val athletes: List<Int>,
)
