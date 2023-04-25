package com.aminovic.obs.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.obs.domain.use_cases.GetAthleteDataUseCase
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
class DetailsViewModel @Inject constructor(
    private val getAthleteDataUseCase: GetAthleteDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())

    val state: StateFlow<DetailsState>
        get() = _state


    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetAthlete -> {
                getAthlete(athleteId = event.athleteId)
            }
        }
    }

    private fun getAthlete(athleteId: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = getAthleteDataUseCase(athleteId = athleteId)) {
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                athlete = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}