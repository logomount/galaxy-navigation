package com.codescape.kepler22.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.codescape.kepler22.kepler22b.KeplerScreen
import com.codescape.kepler22.keplersystem.KeplerSystemScreen
import kotlinx.serialization.Serializable

// Kepler System
object Kepler22Graph {

    @Serializable
    data object ROUTE

    @Serializable
    data object Kepler22SystemScreen

    @Serializable
    data object Kepler22BScreen
}

fun NavGraphBuilder.kepler22Graph(
    navController: NavHostController,
    onSelectSolarSystem: () -> Unit
) {
    navigation<Kepler22Graph.ROUTE>(
        startDestination = Kepler22Graph.Kepler22SystemScreen
    ) {
        composable<Kepler22Graph.Kepler22SystemScreen>(
            deepLinks = listOf(
                navDeepLink<Kepler22Graph.Kepler22SystemScreen>("galaxy://navigation/kepler22")
            )
        ) {
            KeplerSystemScreen(
                onSelectKepler22b = {
                    navController.navigate(Kepler22Graph.Kepler22BScreen)
                }
            )
        }
        composable<Kepler22Graph.Kepler22BScreen>(
            deepLinks = listOf(
                navDeepLink<Kepler22Graph.Kepler22BScreen>("galaxy://navigation/kepler22b")
            )
        ) {
            KeplerScreen(
                onSelectSolarSystem = onSelectSolarSystem
            )
        }
    }
}
