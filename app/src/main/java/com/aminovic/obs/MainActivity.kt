package com.aminovic.obs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aminovic.obs.presentation.Screens
import com.aminovic.obs.presentation.details.DetailsScreen
import com.aminovic.obs.presentation.details.DetailsViewModel
import com.aminovic.obs.presentation.main_screen.MainScreen
import com.aminovic.obs.presentation.main_screen.MainViewModel
import com.aminovic.obs.presentation.ui.theme.OBSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OBSTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { paddings ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.MainScreen.route,
                        modifier = Modifier.padding(paddings)
                    ) {
                        composable(Screens.MainScreen.route) {
                            val mainViewModel: MainViewModel = hiltViewModel()
                            val state by mainViewModel.state.collectAsStateWithLifecycle()
                            MainScreen(
                                state = state,
                                onEvent = mainViewModel::onEvent,
                                onAthleteClicked = { athleteId, AthleteName ->
                                    navController.navigate(
                                        Screens.Details.route + "/$athleteId/$AthleteName"
                                    ) {
                                        launchSingleTop = true
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(
                            route = Screens.Details.route + "/{AthleteId}/{AthleteName}",
                            arguments = listOf(
                                navArgument("AthleteId") {
                                    type = NavType.StringType
                                },
                                navArgument("AthleteName") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val athleteId = it.arguments?.getString("AthleteId")!!
                            val athleteName = it.arguments?.getString("AthleteName")!!
                            val detailsViewModel: DetailsViewModel = hiltViewModel()
                            val state by detailsViewModel.state.collectAsStateWithLifecycle()
                            DetailsScreen(
                                state = state,
                                popup = { navController.popBackStack() },
                                athleteId = athleteId,
                                athleteName = athleteName,
                                onEvent = detailsViewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}