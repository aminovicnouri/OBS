package com.aminovic.obs.ui

sealed class Screens(val route : String) {
    object MainScreen: Screens(route = "main_screen")
    object Details: Screens(route = "details_screen")
}
