package com.aminovic.obs.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aminovic.obs.data.local.Constants.Tables.GAMES_TABLE

@Entity(tableName = GAMES_TABLE)
@TypeConverters(GamesAthletesTypeConverter::class)
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "athletes")
    val athletes: List<AthleteEntity> = emptyList(),
)
