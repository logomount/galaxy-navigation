package com.codescape.solar.mars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import com.codescape.component.SpacePortItem
import com.codescape.component.TravelDestinationItem
import com.codescape.theme.GalaxyNavigationTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.mars
import theme.reshot_icon_earth
import theme.reshot_icon_geology
import theme.Res as ThemeRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsScreen(
    modifier: Modifier = Modifier,
    onSelectKeplerSystem: () -> Unit = {},
    onSelectEarth: (spacePort: String) -> Unit = {}
) {
    var spacePortsExpanded by remember { mutableStateOf(false) }
    var selectedSpacePort by remember { mutableStateOf<String?>(null) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Image(
                painter = painterResource(resource = ThemeRes.drawable.mars),
                contentDescription = "Mars",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(RoundedCornerShape(32.dp)),
                contentScale = ContentScale.Crop
            )
        }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Destinations",
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            TravelDestinationItem(
                icon = painterResource(
                    resource = ThemeRes.drawable.reshot_icon_earth
                ),
                title = "Earth",
                subtitle = "Solar System",
                trailingIcon = if (spacePortsExpanded) Icons.Rounded.ArrowDropUp
                else Icons.Rounded.ArrowDropDown,
                onClick = {
                    spacePortsExpanded = !spacePortsExpanded
                }
            )
            if (spacePortsExpanded) {
                Spacer(Modifier.height(16.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Space Ports",
                        style = MaterialTheme.typography.titleSmall,
                    )
                    SpacePortItem(
                        name = "Kennedy Space Center",
                        location = "USA",
                        isSelected = selectedSpacePort == "Kennedy Space Center",
                        onClick = {
                            selectedSpacePort = "Kennedy Space Center"
                        }
                    )
                    SpacePortItem(
                        name = "Baikonur Cosmodrome",
                        location = "Kazakhstan",
                        isSelected = selectedSpacePort == "Baikonur Cosmodrome",
                        onClick = {
                            selectedSpacePort = "Baikonur Cosmodrome"
                        }
                    )
                    SpacePortItem(
                        name = "Tanegashima Space Center",
                        location = "Japan",
                        isSelected = selectedSpacePort == "Tanegashima Space Center",
                        onClick = {
                            selectedSpacePort = "Tanegashima Space Center"
                        }
                    )
                }
            }
        }
        item {
            TravelDestinationItem(
                icon = painterResource(
                    resource = ThemeRes.drawable.reshot_icon_geology
                ),
                title = "Kepler-22b",
                subtitle = "Kepler-22 System",
                onClick = {
                    onSelectKeplerSystem()
                }
            )
        }
        item {
            Button(
                onClick = dropUnlessResumed {
                    selectedSpacePort?.let(onSelectEarth)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(16.dp),
                enabled = selectedSpacePort != null
            ) {
                Text(
                    text = if (selectedSpacePort != null) "Arrive at $selectedSpacePort"
                    else "Select a space port",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun MarsScreenPreview() {
    GalaxyNavigationTheme {
        Scaffold {
            MarsScreen()
        }
    }
}