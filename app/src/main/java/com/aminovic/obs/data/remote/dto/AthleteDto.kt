package com.aminovic.obs.data.remote.dto

import com.squareup.moshi.Json

data class AthleteDto(
    @field:Json(name = "athlete_id")
    val athleteId: String,
    val name: String? = null,
    val surname: String? = null,
    val dateOfBirth: String? = null,
    val bio: String? = null,
    val weight: Int? = null,
    val height: Int? = null,
    @field:Json(name = "photo_id")
    val photoId: Int? = null,
)
