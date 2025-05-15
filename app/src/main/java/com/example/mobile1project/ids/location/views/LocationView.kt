package com.example.mobile1project.ids.location.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.ids.location.viewmodels.LocationViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListScreen(viewModel: LocationViewModel = viewModel()) {
    val locations = viewModel.locations.collectAsState(initial = emptyList())
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Locations List") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(locations.value) { location ->
                val cleanImageName = location.imageUrl?.lowercase(Locale.getDefault()) ?: ""
                val imageId = getDrawableId(context, cleanImageName)

                Card(
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        if (imageId != 0) {
                            Image(
                                painter = painterResource(id = imageId),
                                contentDescription = "Imagen de ${location.name}",
                                modifier = Modifier
                                    .size(64.dp)
                                    .aspectRatio(1f),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(64.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Sin imagen")
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(text = location.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = location.address, style = MaterialTheme.typography.bodyMedium)
                            Text(
                                text = "Lat: ${location.latitude}, Lng: ${location.longitude}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getDrawableId(context: android.content.Context, imageName: String): Int {
    return context.resources.getIdentifier(imageName.lowercase(), "drawable", context.packageName)
}
