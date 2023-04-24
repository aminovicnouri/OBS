package com.aminovic.obs.ui.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.obs.domain.repository.ObsRepository
import com.aminovic.obs.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ObsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())

    val state: StateFlow<MainState>
        get() = _state

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch {
            repository.getGames().collect { listOfGames ->
                Log.d("hhhhhhhhh", "$listOfGames")
                _state.update { it.copy(isLoading = false, error = null, gamesList = listOfGames) }
            }
        }
        loadDataFormApi()
    }

    private fun loadDataFormApi() {
        viewModelScope.launch() {
            _state.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO) {
                when (val result = repository.getGamesData()) {
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                    is Resource.Success -> {
                        result.data?.let { gamesList ->
                            val games = gamesList.associateBy { it.id }
                            games.forEach { (id, game) ->
                                when (val gameAthletes = repository.getGameAthletes(game.id)) {
                                    is Resource.Error -> {
                                        _state.update {
                                            it.copy(
                                                isLoading = false,
                                                error = gameAthletes.message
                                            )
                                        }
                                    }
                                    is Resource.Success -> {
                                        gameAthletes.data?.onEach { athlete ->
                                            when (val athleteResult =
                                                repository.getAthleteResults(athlete.athleteId)) {
                                                is Resource.Error -> {
                                                    _state.update {
                                                        it.copy(
                                                            isLoading = false,
                                                            error = athleteResult.message
                                                        )
                                                    }
                                                }
                                                is Resource.Success -> {
                                                    val ath = athlete.copy(
                                                        results = athleteResult.data ?: emptyList()
                                                    )
                                                    game.athletes.add(ath)
                                                }
                                            }
                                        }
                                        repository.insertGame(game)
                                    }
                                }
                            }
                        }
                        _state.update { it.copy(isLoading = false, error = null) }
                    }
                }
            }
        }
    }
}