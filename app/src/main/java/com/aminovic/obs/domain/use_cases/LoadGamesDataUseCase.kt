package com.aminovic.obs.domain.use_cases

import com.aminovic.obs.domain.repository.ObsRepository
import com.aminovic.obs.domain.utils.Resource
import javax.inject.Inject

class LoadGamesDataUseCase @Inject constructor(
    private val repository: ObsRepository
) {
    suspend operator fun invoke(): String? {

        when (val result = repository.getGamesData()) {
            is Resource.Error -> {
                return result.message ?: "Unknown error occurred"
            }
            is Resource.Success -> {
                result.data?.let { gamesList ->
                    val games = gamesList.associateBy { it.id }
                    games.forEach { (_, game) ->
                        when (val gameAthletes = repository.getGameAthletes(game.id)) {
                            is Resource.Error -> {
                                return result.message ?: "Unknown error occurred"
                            }
                            is Resource.Success -> {
                                gameAthletes.data?.onEach { athlete ->
                                    when (val athleteResult =
                                        repository.getAthleteResults(athlete.athleteId)) {
                                        is Resource.Error -> {
                                            return result.message ?: "Unknown error occurred"
                                        }
                                        is Resource.Success -> {
                                            val results = athleteResult.data ?: emptyList()
                                            val resultsMap =
                                                results.associateBy { it.city }
                                            val gameResult = resultsMap[game.city]
                                            val score =
                                                if (gameResult != null && gameResult.year == game.year) {
                                                    gameResult.gold!! * 5 + gameResult.silver!! * 3 + gameResult.bronze!!
                                                } else {
                                                    0
                                                }
                                            val ath = athlete.copy(
                                                results = results,
                                                score = score
                                            )
                                            game.athletes.add(ath)
                                        }
                                    }
                                }
                                game.athletes.sortByDescending { it.score }
                                repository.insertGame(game)
                            }
                        }
                    }
                }
                return null
            }
        }
    }
}