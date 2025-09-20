package com.codescape.kepler22.keplersystem

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
import androidx.compose.material3.Surface
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
import theme.kepler_system
import theme.reshot_icon_geology
import theme.Res as ThemeRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeplerSystemScreen(
    modifier: Modifier = Modifier,
    onSelectKepler22b: () -> Unit = {}
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
                painter = painterResource(resource = ThemeRes.drawable.kepler_system),
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
                text = "Planets",
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            TravelDestinationItem(
                icon = painterResource(
                    resource = ThemeRes.drawable.reshot_icon_geology
                ),
                title = "Kepler-22b",
                subtitle = "Default Port",
                onClick = {
                    onSelectKepler22b()
                }
            )
        }
    }
}

@Preview
@Composable
fun KeplerSystemScreenPreview() {
    GalaxyNavigationTheme {
        Surface {
            KeplerSystemScreen()
        }
    }
}
