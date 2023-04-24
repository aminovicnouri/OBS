package com.aminovic.obs.domain.repository

import com.aminovic.obs.data.remote.dto.AthleteDto
import com.aminovic.obs.data.remote.dto.GameDto
import com.aminovic.obs.data.remote.dto.ResultDto
import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.domain.modal.Game
import com.aminovic.obs.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ObsRepository {

    // local
    suspend fun insertAthletes(athletes: List<Athlete>)

    fun getAthletes(): Flow<List<Athlete>>

    suspend fun getAthlete(id: Int): Athlete?

    suspend fun deleteAthletes()

    suspend fun insertGame(game: Game)

    suspend fun deleteGames()

    // remote
    suspend fun getAthletesData(): Resource<List<AthleteDto>>

    suspend fun getAthleteData(id: Int): Resource<AthleteDto>

    suspend fun getAthleteResults(id: Int): Resource<List<ResultDto>>

    suspend fun getGames(): Resource<List<GameDto>>

    suspend fun getGameAthletes(id: Int): Resource<GameDto>
}