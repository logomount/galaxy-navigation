package com.codescape.galaxynavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavUri
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.codescape.galaxynavigation.navigation.AppNavigation
import com.codescape.kepler22.navigation.Kepler22Graph
import com.codescape.solar.navigation.SolarGraph
import com.codescape.theme.GalaxyNavigationTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.reshot_icon_galaxy
import theme.reshot_icon_solar_system
import theme.Res as ThemeRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    GalaxyNavigationTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val hasBackButton = remember(backStackEntry) {
            backStackEntry?.run {
                when {
                    destination.hasRoute<SolarGraph.SolarSystemScreen>() -> false
                    destination.hasRoute<Kepler22Graph.Kepler22SystemScreen>() -> false
                    else -> true
                }
            } ?: false
        }
        val toolbarTitle = remember(backStackEntry) {
            backStackEntry?.run {
                when {
                    destination.hasRoute<SolarGraph.SolarSystemScreen>() -> "Solar System"
                    destination.hasRoute<SolarGraph.EarthScreen>() ->
                        toRoute<SolarGraph.EarthScreen>().spacePort

                    destination.hasRoute<SolarGraph.MarsScreen>() -> "Mars"
                    destination.hasRoute<Kepler22Graph.Kepler22SystemScreen>() -> "Kepler-22 System"
                    destination.hasRoute<Kepler22Graph.Kepler22BScreen>() -> "Kepler-22b"

                    else -> ""
                }
            }.orEmpty()
        }
        val bottomNavItem = remember(backStackEntry) {
            backStackEntry?.destination?.run {
                when {
                    hierarchy.any { it.hasRoute<SolarGraph.ROUTE>() } -> BottomNavItem.SolarSystem
                    hierarchy.any { it.hasRoute<Kepler22Graph.ROUTE>() } -> BottomNavItem.Kepler22System
                    else -> null
                }
            }
        }
        val snackHostState = remember { SnackbarHostState() }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(toolbarTitle) },
                    navigationIcon = {
                        if (hasBackButton) {
                            IconButton(
                                onClick = { navController.navigateUp() }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            bottomBar = {
                BottomNavigation(
                    selectedItem = bottomNavItem,
                    onItemSelected = {
                        val route = when (it) {
                            BottomNavItem.SolarSystem -> SolarGraph.ROUTE
                            BottomNavItem.Kepler22System -> Kepler22Graph.ROUTE
                        }
                        navController.navigate(route = route) {
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(snackHostState) { snackbarData ->
                    Snackbar(snackbarData = snackbarData)
                }
            }
        ) { paddingValues ->
            AppNavigation(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
        val currentState by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsState()
        LaunchedEffect(currentState) {
            if (currentState == Lifecycle.State.RESUMED) {
                launch {
                    ExternalUriHandler.uriFlow.collect { uri ->
                        try {
                            navController.navigate(NavUri(uri))
                        } catch (e: Exception) {
                            snackHostState.showSnackbar(
                                message = "Invalid deep link: $uri",
                                duration = SnackbarDuration.Short,
                                withDismissAction = true
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class BottomNavItem { SolarSystem, Kepler22System }

@Composable
fun BottomNavigation(
    selectedItem: BottomNavItem?,
    onItemSelected: (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        NavigationBarItem(
            selected = selectedItem == BottomNavItem.SolarSystem,
            onClick = dropUnlessResumed {
                onItemSelected(BottomNavItem.SolarSystem)
            },
            icon = {
                Icon(
                    painter = painterResource(
                        resource = ThemeRes.drawable.reshot_icon_solar_system
                    ),
                    contentDescription = "Solar System"
                )
            },
            label = {
                Text("Solar System")
            }
        )
        NavigationBarItem(
            selected = selectedItem == BottomNavItem.Kepler22System,
            onClick = dropUnlessResumed {
                onItemSelected(BottomNavItem.Kepler22System)
            },
            icon = {
                Icon(
                    painter = painterResource(
                        resource = ThemeRes.drawable.reshot_icon_galaxy
                    ),
                    contentDescription = "Kepler System"
                )
            },
            label = { Text("Kepler System") }
        )
    }
}