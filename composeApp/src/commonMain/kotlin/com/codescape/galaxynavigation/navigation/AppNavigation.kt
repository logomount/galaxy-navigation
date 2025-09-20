package com.codescape.galaxynavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.codescape.kepler22.navigation.Kepler22Graph
import com.codescape.kepler22.navigation.kepler22Graph
import com.codescape.solar.navigation.SolarGraph
import com.codescape.solar.navigation.sunGraph

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SolarGraph.ROUTE,
        contentAlignment = Alignment.Center
    ) {
        sunGraph(
            navController = navController,
            onSelectKepler22System = {
                navController.navigate(Kepler22Graph.ROUTE)
            }
        )
        kepler22Graph(
            navController = navController,
            onSelectSolarSystem = {
                navController.navigate(SolarGraph.ROUTE)
            }
        )
    }
}
