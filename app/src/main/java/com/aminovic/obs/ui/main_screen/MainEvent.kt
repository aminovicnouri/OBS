package com.aminovic.obs.ui.main_screen

sealed class MainEvent {
    data class navigateToDetails(val athleteId: Int): MainEvent()
}
