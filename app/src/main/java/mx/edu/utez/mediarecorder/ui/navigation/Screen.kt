package mx.edu.utez.mediarecorder.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Recording : Screen("recording", "Grabar", Icons.Default.Mic)
    object AudioList : Screen("audio", "Audios", Icons.Default.AudioFile)
    object ImageList : Screen("images", "Imágenes", Icons.Default.Image)
    object VideoList : Screen("videos", "Videos", Icons.Default.Videocam)
    // Pantalla de detalle (no va en la barra de navegación)
    object VideoPlayer : Screen("video_player/{uri}", "Video Player", Icons.Default.Videocam) {
        fun createRoute(uri: String) = "video_player/$uri"
    }
}
val navBarItems = listOf(
    Screen.Recording,
    Screen.AudioList,
    Screen.ImageList,
    Screen.VideoList,
)