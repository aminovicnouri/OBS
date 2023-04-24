package com.aminovic.obs.data.repository

import com.aminovic.obs.data.local.ObsDao
import com.aminovic.obs.data.mappers.toAthlete
import com.aminovic.obs.data.mappers.toAthleteEntity
import com.aminovic.obs.data.mappers.toGameEntity
import com.aminovic.obs.data.remote.ObsApi
import com.aminovic.obs.data.remote.dto.AthleteDto
import com.aminovic.obs.data.remote.dto.GameDto
import com.aminovic.obs.data.remote.dto.ResultDto
import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.domain.modal.Game
import com.aminovic.obs.domain.repository.ObsRepository
import com.aminovic.obs.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObsRepositoryImpl(
    private val dao: ObsDao,
    private val api: ObsApi
) : ObsRepository {
    override suspend fun insertAthletes(athletes: List<Athlete>) {
        dao.insertAthletes(athletes.map { it.toAthleteEntity() })
    }

    override fun getAthletes(): Flow<List<Athlete>> {
        return dao.getAthletes().map { listOfEntities ->
            listOfEntities.map { it.toAthlete() }
        }
    }

    override suspend fun getAthlete(id: Int): Athlete? {
        return dao.getAthlete(id = id)?.toAthlete()
    }

    override suspend fun deleteAthletes() {
        dao.deleteAthletes()
    }

    override suspend fun insertGame(game: Game) {
        dao.insertGame(game.toGameEntity())
    }

    override suspend fun deleteGames() {
        dao.deleteGames()
    }

    override suspend fun getAthletesData(): Resource<List<AthleteDto>> {
        return api.getAthletes()
    }

    override suspend fun getAthleteData(id: Int): Resource<AthleteDto> {
        return api.getAthlete(id)
    }

    override suspend fun getAthleteResults(id: Int): Resource<List<ResultDto>> {
        return api.getAthleteResults(id)
    }

    override suspend fun getGames(): Resource<List<GameDto>> {
        return api.getGames()
    }

    override suspend fun getGameAthletes(id: Int): Resource<GameDto> {
        return api.getGameAthletes(id = id)
    }
}