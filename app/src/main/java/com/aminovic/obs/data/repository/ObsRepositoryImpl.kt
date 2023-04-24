package com.aminovic.obs.data.repository

import com.aminovic.obs.data.local.ObsDao
import com.aminovic.obs.data.mappers.*
import com.aminovic.obs.data.remote.ObsApi
import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.domain.modal.AthleteResult
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

    override suspend fun getAthletesByIds(ids: List<String>): List<Athlete> {
        return dao.getAthletesByIds(ids)?.map { it.toAthlete() } ?: emptyList()
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

    override fun getGames(): Flow<List<Game>> {
        return dao.getGames().map { list ->
            list.map { it.toGame() }
        }
    }

    override suspend fun deleteGames() {
        dao.deleteGames()
    }


    override suspend fun getAthleteData(id: Int): Resource<Athlete> {
        return try {
            Resource.Success(api.getAthlete(id).toAthlete())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error occurred")
        }
    }

    override suspend fun getAthleteResults(id: String): Resource<List<AthleteResult>> {
        return try {
            Resource.Success(api.getAthleteResults(id).map { it.toAthleteResult() })
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error occurred")
        }
    }

    override suspend fun getGamesData(): Resource<List<Game>> {
        return try {
            Resource.Success(api.getGames().map { it.toGame() })
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error occurred")
        }
    }

    override suspend fun getGameAthletes(id: Int): Resource<List<Athlete>> {
        return try {
            Resource.Success(api.getGameAthletes(id = id).map { it.toAthlete() })
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error occurred")
        }
    }
}