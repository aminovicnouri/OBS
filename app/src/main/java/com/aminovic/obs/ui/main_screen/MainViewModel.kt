package com.aminovic.obs.ui.main_screen

import com.aminovic.obs.domain.repository.ObsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ObsRepository
) {

}