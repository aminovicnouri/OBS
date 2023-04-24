package com.aminovic.obs.data.mappers

import com.aminovic.obs.data.local.entities.AthleteEntity
import com.aminovic.obs.data.remote.dto.AthleteDto
import com.aminovic.obs.domain.modal.Athlete

fun AthleteDto.toAthlete(): Athlete {
    return Athlete(
        athleteId = athleteId,
        name = name,
        surname = surname,
        dateOfBirth = dateOfBirth,
        bio = bio,
        weight = weight,
        height = height,
        photoId = photoId,
    )
}
fun Athlete.toAthleteEntity(): AthleteEntity {
    return AthleteEntity(
        athleteId = athleteId,
        name = name,
        surname = surname,
        dateOfBirth = dateOfBirth,
        bio = bio,
        weight = weight,
        height = height,
        photoId = photoId,
    )
}
fun AthleteEntity.toAthlete(): Athlete {
    return Athlete(
        athleteId = athleteId,
        name = name,
        surname = surname,
        dateOfBirth = dateOfBirth,
        bio = bio,
        weight = weight,
        height = height,
        photoId = photoId,
        results = results.map { it.toAthleteResult() }
    )
}