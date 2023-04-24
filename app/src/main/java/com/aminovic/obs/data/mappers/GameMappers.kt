package com.aminovic.obs.data.mappers

import com.aminovic.obs.data.local.entities.GameEntity
import com.aminovic.obs.data.remote.dto.GameDto
import com.aminovic.obs.domain.modal.Game

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        city = city,
        year = year,
    )
}

fun Game.toGameEntity(): GameEntity {
    return GameEntity(
        id = id,
        city = city,
        year = year,
        athletes = athletes.map { it.toAthleteEntity() }
    )
}

fun GameEntity.toGame(): Game {
    return Game(
        id = id,
        city = city,
        year = year,
        athletes = ArrayList(athletes.map { it.toAthlete() })
    )
}