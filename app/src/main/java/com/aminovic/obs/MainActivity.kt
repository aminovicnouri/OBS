package com.aminovic.obs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aminovic.obs.ui.Screens
import com.aminovic.obs.ui.main_screen.MainScreen
import com.aminovic.obs.ui.theme.OBSTheme
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
                            MainScreen()
                        }
//                        composable(
//                            route = Screens.Details.route + "/{AthleteId}",
//                            arguments = listOf(
//                                navArgument("AthleteId") {
//                                    type = NavType.StringType
//                                }
//                            )
//                        ) {
//                            val id = it.arguments?.getString("AthleteId")!!
//
//                        }
                    }
                }
            }
        }
    }
}