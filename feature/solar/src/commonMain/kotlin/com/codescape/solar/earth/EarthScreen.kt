package com.codescape.solar.earth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.codescape.component.TravelDestinationItem
import com.codescape.theme.GalaxyNavigationTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.earth
import theme.reshot_icon_geology
import theme.reshot_icon_mars
import theme.Res as ThemeRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarthScreen(
    modifier: Modifier = Modifier,
    onSelectKeplerSystem: () -> Unit = {},
    onSelectMars: () -> Unit = {}
) {
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
                painter = painterResource(resource = ThemeRes.drawable.earth),
                contentDescription = "Earth",
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
                    resource = ThemeRes.drawable.reshot_icon_mars
                ),
                title = "Mars",
                subtitle = "Solar System",
                onClick = {
                    onSelectMars()
                }
            )
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
    }
}

@Preview
@Composable
fun EarthScreenPreview() {
    GalaxyNavigationTheme {
        Scaffold {
            EarthScreen()
        }
    }
}