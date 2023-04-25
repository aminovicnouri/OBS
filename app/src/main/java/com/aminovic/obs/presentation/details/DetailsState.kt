package com.aminovic.obs.presentation.details

import com.aminovic.obs.domain.modal.Athlete

data class DetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val athlete: Athlete? = null
)
