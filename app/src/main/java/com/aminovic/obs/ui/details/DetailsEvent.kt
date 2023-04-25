package com.aminovic.obs.ui.details

sealed class DetailsEvent {
    data class GetAthlete(val athleteId: String) : DetailsEvent()
}
