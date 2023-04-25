package com.aminovic.obs.ui.details

import com.aminovic.obs.domain.modal.Athlete

data class DetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val athlete: Athlete? = null
)
