package com.aminovic.obs.data.remote.dto

import com.squareup.moshi.Json

data class GameDto(
    @field:Json(name = "game_id")
    val id: Int,
    val city: String,
    val year: Int,
)
