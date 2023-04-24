package com.aminovic.obs.ui.main_screen

import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.domain.modal.Game

data class MainState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val gamesList: List<Game> = emptyList(),
    val athletes: List<Athlete> = emptyList()
)
