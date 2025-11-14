package mx.edu.utez.mediarecorder.ui.screens;

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mx.edu.utez.mediarecorder.data.MediaItem
import mx.edu.utez.mediarecorder.ui.navigation.Screen
import mx.edu.utez.mediarecorder.viewmodel.MediaViewModel
import okhttp3.internal.concurrent.formatDuration


// --- 18. Screen 4: Lista de Videos ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListScreen(
    mediaViewModel: MediaViewModel,
    navController: NavController
) {
    val videoList by mediaViewModel.allVideos.collectAsState()
    Scaffold(
            topBar = {
                    CenterAlignedTopAppBar(title = { Text("Mis Videos") })
            }
    ) { padding ->
            LazyColumn(
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
        if (videoList.isEmpty()) {
            item {
                Text(
                        "No hay videos grabados.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
        items(videoList) { item ->

                VideoCard(
                        item = item,
                        onClick = {
// Navegar al reproductor. La URI debe ser codificada.
                                val encodedUri = Uri.encode(item.uri)
                                navController.navigate(Screen.VideoPlayer.createRoute(encodedUri))
                        }
                )
        }
    }
    }
}
@Composable
fun VideoCard(item: MediaItem, onClick: () -> Unit) {
Card(
        modifier = Modifier
                .fillMaxWidth()
.clickable { onClick() },
elevation = CardDefaults.cardElevation(4.dp)
) {
Row(
        modifier = Modifier
                .fillMaxWidth()
.padding(16.dp),
verticalAlignment = Alignment.CenterVertically
) {
// Miniatura (usando Coil)
AsyncImage(
        model = item.uri.toUri(),
contentDescription = "Miniatura",
modifier = Modifier
        .size(80.dp, 60.dp)
.clip(RoundedCornerShape(8.dp))
        .background(Color.Gray),
contentScale = ContentScale.Crop
)
Spacer(Modifier.width(16.dp))
Column(modifier = Modifier.weight(1f)) {
Text(
        text = item.name,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
)
Text(
        text = formatDuration(item.duration),
style = MaterialTheme.typography.bodySmall
)
Text(
        text = formatDate(item.date),
style = MaterialTheme.typography.bodySmall,
color = Color.Gray
)
        }
        }
        }
        }