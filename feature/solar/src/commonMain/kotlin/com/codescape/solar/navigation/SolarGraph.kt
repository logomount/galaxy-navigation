package com.codescape.solar.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.codescape.solar.earth.EarthScreen
import com.codescape.solar.mars.MarsScreen
import com.codescape.solar.solarsystem.SolarSystemScreen
import kotlinx.serialization.Serializable

// Solar System
object SolarGraph {

    @Serializable
    data object ROUTE

    @Serializable
    data object SolarSystemScreen

    @Serializable
    data class EarthScreen(
        val spacePort: String
    )

    @Serializable
    data object MarsScreen
}

fun NavGraphBuilder.solarGraph(
    navController: NavController,
    onSelectKepler22System: () -> Unit
) {
    navigation<SolarGraph.ROUTE>(
        startDestination = SolarGraph.SolarSystemScreen
    ) {
        composable<SolarGraph.SolarSystemScreen>(
            deepLinks = listOf(
                navDeepLink<SolarGraph.SolarSystemScreen>("galaxy://navigation/solar")
            )
        ) {
            SolarSystemScreen(
                onSelectEarth = { spacePort ->
                    navController.navigate(SolarGraph.EarthScreen(spacePort = spacePort))
                },
                onSelectMars = {
                    navController.navigate(SolarGraph.MarsScreen)
                }
            )
        }
        composable<SolarGraph.EarthScreen>(
            deepLinks = listOf(
                navDeepLink<SolarGraph.EarthScreen>("galaxy://navigation/earth")
            )
        ) {
            val spacePort = it.toRoute<SolarGraph.EarthScreen>()
            EarthScreen(
                onSelectKeplerSystem = onSelectKepler22System,
                onSelectMars = {
                    navController.navigate(SolarGraph.MarsScreen)
                }
            )
        }
        composable<SolarGraph.MarsScreen>(
            deepLinks = listOf(
                navDeepLink<SolarGraph.EarthScreen>("galaxy://navigation/mars")
            )
        ) {
            MarsScreen(
                onSelectKeplerSystem = onSelectKepler22System,
                onSelectEarth = { spacePort ->
                    navController.navigate(SolarGraph.EarthScreen(spacePort = spacePort))
                }
            )
        }
    }
}
