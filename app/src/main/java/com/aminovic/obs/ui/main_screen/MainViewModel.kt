package com.aminovic.obs.ui.main_screen

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

    private fun init() {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            repository.getGames().collect { listOfGames ->
                _state.update { it.copy(gamesList = listOfGames) }
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val error = loadGamesDataUseCase()
                _state.update { it.copy(isLoading = false, error = error) }
            }
        }
    }

}