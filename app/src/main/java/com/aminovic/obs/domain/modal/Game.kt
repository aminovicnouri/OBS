package com.aminovic.obs.domain.modal

data class Game(
    val id: Int,
    val city: String,
    val year: Int,
    var athletes: ArrayList<Athlete> = ArrayList()
)
