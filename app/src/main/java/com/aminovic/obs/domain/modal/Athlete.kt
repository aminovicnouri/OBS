package com.aminovic.obs.domain.modal


data class Athlete(
    val athleteId: String,
    val name: String? = null,
    val surname: String? = null,
    val dateOfBirth: String? = null,
    val bio: String? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val photoId: Int? = null,
    val results: List<AthleteResult> = emptyList(),
    var score: Int = 0
)
