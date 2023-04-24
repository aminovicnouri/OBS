package com.aminovic.obs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aminovic.obs.data.local.entities.AthleteEntity
import com.aminovic.obs.data.local.entities.GameEntity

@Database(
    entities = [AthleteEntity::class, GameEntity::class],
    version = 1
)
abstract class ObsDatabase: RoomDatabase() {
    abstract val dao: ObsDao
}