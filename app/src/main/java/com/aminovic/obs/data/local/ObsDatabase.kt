package com.aminovic.obs.data.local

import androidx.room.Database
import com.aminovic.obs.data.local.entities.AthleteEntity
import com.aminovic.obs.data.local.entities.GameEntity

@Database(
    entities = [AthleteEntity::class, GameEntity::class],
    version = 1
)
abstract class ObsDatabase {
    abstract val dao: ObsDao
}