package com.aminovic.obs.data.local

import androidx.room.*
import com.aminovic.obs.data.local.Constants.Tables.ATHLETES_TABLE
import com.aminovic.obs.data.local.Constants.Tables.GAMES_TABLE
import com.aminovic.obs.data.local.entities.AthleteEntity
import com.aminovic.obs.data.local.entities.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ObsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAthletes(entities: List<AthleteEntity>)

    @Query("SELECT * FROM $ATHLETES_TABLE")
    fun getAthletes(): Flow<List<AthleteEntity>>

    @Query(
        """
            SELECT *
            FROM $ATHLETES_TABLE
            WHERE athlete_id = :id
        """
    )

    suspend fun getAthlete(id: Int): AthleteEntity?


    @Query("DELETE FROM $ATHLETES_TABLE")
    suspend fun deleteAthletes()

    @Transaction
    suspend fun deleteAndInsertAthletes(entities: List<AthleteEntity>) {
        deleteAthletes()
        insertAthletes(entities)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(entities: GameEntity)

    @Query("SELECT * FROM $GAMES_TABLE")
    fun getGames(): Flow<List<GameEntity>>

    @Query("DELETE FROM $GAMES_TABLE")
    suspend fun deleteGames()
}