package com.aminovic.obs.presentation.details

sealed class DetailsEvent {
    data class GetAthlete(val athleteId: String) : DetailsEvent()
}
