package com.aminovic.obs.domain.repository

import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.domain.modal.AthleteResult
import com.aminovic.obs.domain.modal.Game
import com.aminovic.obs.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ObsRepository {

    // local
    suspend fun insertAthletes(athletes: List<Athlete>)

    fun getAthletes(): Flow<List<Athlete>>

    suspend fun getAthletesByIds(ids: List<String>): List<Athlete>

    suspend fun deleteAthletes()

    suspend fun insertGame(game: Game)

    fun getGames(): Flow<List<Game>>

    suspend fun deleteGames()

    // remote
    suspend fun getAthleteData(id: String): Resource<Athlete>

    suspend fun getAthleteResults(id: String): Resource<List<AthleteResult>>

    suspend fun getGamesData(): Resource<List<Game>>

    suspend fun getGameAthletes(id: Int): Resource<List<Athlete>>
}