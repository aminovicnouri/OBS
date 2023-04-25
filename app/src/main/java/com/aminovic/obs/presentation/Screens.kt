package com.aminovic.obs.presentation

sealed class Screens(val route: String) {
    object MainScreen : Screens(route = "main_screen")
    object Details : Screens(route = "details_screen")
}
