package com.aminovic.obs.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aminovic.obs.data.local.Constants.Tables.ATHLETES_TABLE

@Entity(tableName = ATHLETES_TABLE)
@TypeConverters(ResultsEntityTypeConverter::class)
data class AthleteEntity(
    @PrimaryKey
    @ColumnInfo(name = "athlete_id")
    val athleteId: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "surname")
    val surname: String? = null,

    @ColumnInfo(name = "dateOfBirth")
    val dateOfBirth: String? = null,

    @ColumnInfo(name = "bio")
    val bio: String? = null,

    @ColumnInfo(name = "weight")
    val weight: Int? = null,

    @ColumnInfo(name = "height")
    val height: Int? = null,

    @ColumnInfo(name = "photoId")
    val photoId: Int? = null,
)
