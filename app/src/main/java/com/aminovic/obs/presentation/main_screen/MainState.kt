package com.aminovic.obs.presentation.main_screen

import com.aminovic.obs.domain.modal.Game

data class MainState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val gamesList: List<Game> = emptyList()
)
