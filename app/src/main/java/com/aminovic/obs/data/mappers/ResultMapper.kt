package com.aminovic.obs.data.mappers

import com.aminovic.obs.data.local.entities.ResultEntity
import com.aminovic.obs.data.remote.dto.ResultDto
import com.aminovic.obs.domain.modal.AthleteResult

fun ResultDto.toAthleteResult(): AthleteResult {
    return AthleteResult(
        city = city,
        year = year,
        gold = gold,
        silver = silver,
        bronze = bronze
    )
}

fun AthleteResult.toResultEntity(): ResultEntity {
    return ResultEntity(
        city = city,
        year = year,
        gold = gold,
        silver = silver,
        bronze = bronze,
    )
}

fun ResultEntity.toAthleteResult(): AthleteResult {
    return AthleteResult(
        city = city,
        year = year,
        gold = gold,
        silver = silver,
        bronze = bronze,
    )
}