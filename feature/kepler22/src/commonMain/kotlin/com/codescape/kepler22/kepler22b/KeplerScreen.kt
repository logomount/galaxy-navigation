package com.codescape.kepler22.kepler22b

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import com.codescape.component.TravelDestinationItem
import com.codescape.theme.GalaxyNavigationTheme
import kepler22.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.kepler
import theme.reshot_icon_geology
import theme.Res as ThemeRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeplerScreen(
    onSelectSolarSystem: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(resource = ThemeRes.drawable.kepler),
            contentDescription = "Kepler-22b Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Kepler-22b",
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White
                    )

                    Text(
                        text = "An exoplanet orbiting within the habitable zone of the Sun-like star Kepler-22.",
                        color = Color.LightGray,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }
            item {
                TravelDestinationItem(
                    icon = painterResource(
                        resource = ThemeRes.drawable.reshot_icon_geology
                    ),
                    title = "Solar System",
                    subtitle = "Default Port",
                    onClick = dropUnlessResumed {
                        onSelectSolarSystem()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun KeplerScreenPreview() {
    GalaxyNavigationTheme {
        Surface {
            KeplerScreen()
        }
    }
}
