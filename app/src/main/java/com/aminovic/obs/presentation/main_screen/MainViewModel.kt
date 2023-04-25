package com.aminovic.obs.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.obs.domain.repository.ObsRepository
import com.aminovic.obs.domain.use_cases.LoadGamesDataUseCase
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
    private val repository: ObsRepository,
    private val loadGamesDataUseCase: LoadGamesDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())

    val state: StateFlow<MainState>
        get() = _state

    init {
        init()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetGames -> {
                getGames()
            }
        }
    }

    private fun init() {
        getGames()
        viewModelScope.launch {
            repository.getGames().collect { listOfGames ->
                _state.update { it.copy(gamesList = listOfGames) }
            }
        }
    }

    private fun getGames() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _state.update { it.copy(isLoading = true, error = null) }
                val error = loadGamesDataUseCase()
                _state.update { it.copy(isLoading = false, error = error) }
            }
        }
    }
}