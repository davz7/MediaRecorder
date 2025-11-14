package mx.edu.utez.mediarecorder.ui.screens;

import androidx.compose.runtime.Composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.google.accompanist.pager.HorizontalPagerIndicator

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext

import androidx.core.net.toUri

import mx.edu.utez.mediarecorder.viewmodel.MediaViewModel

// --- 17. Screen 3: Carrusel de Imágenes ---
@OptIn(Exper imentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ImageListScreen(mediaViewModel: MediaViewModel) {
    val imageList by mediaViewModel.allImages.collectAsState()
    val pagerState = rememberPagerState(pageCount = { imageList.size })
    Scaffold(
            topBar = {
                    CenterAlignedTopAppBar(title = { Text("Mis Imágenes") })
            }
    ) { padding ->
            Column(
                    modifier = Modifier
                            .fillMaxSize()

                            .padding(padding)
            ) {
        if (imageList.isEmpty()) {
            Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
            ) {
                Text("No hay imágenes capturadas.")
            }
        } else {
            HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                    ) { page ->
                    val item = imageList[page]
                AsyncImage(
                        model = item.uri.toUri(),
                        contentDescription = item.name,
                        modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Fit
                )
            }
// Indicador de página (opcional pero recomendado)
            if (pagerState.pageCount > 1) {
                com.google.accompanist.pager.HorizontalPagerIndicator(
                        pagerState = pagerState,
                        pageCount = pagerState.pageCount,
                        modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                        )
            }
        }
    }
    }
}