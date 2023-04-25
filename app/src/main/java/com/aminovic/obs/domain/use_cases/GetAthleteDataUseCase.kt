package com.aminovic.obs.domain.use_cases

import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.domain.repository.ObsRepository
import com.aminovic.obs.domain.utils.Resource
import javax.inject.Inject

class GetAthleteDataUseCase @Inject constructor(
    private val repository: ObsRepository
) {
    suspend operator fun invoke(athleteId: String): Resource<Athlete> {

        when (val athlete = repository.getAthleteData(athleteId)) {
            is Resource.Error -> {
                return athlete
            }
            is Resource.Success -> {
                athlete.data?.let { ath ->
                    when (val results = repository.getAthleteResults(athleteId)) {
                        is Resource.Error -> {
                            return Resource.Success(ath)
                        }
                        is Resource.Success -> {
                            return Resource.Success(ath.copy(results = results.data!!))
                        }
                    }
                } ?: kotlin.run {
                    return Resource.Error(message = "Unknown error occured")
                }
            }
        }
    }
}