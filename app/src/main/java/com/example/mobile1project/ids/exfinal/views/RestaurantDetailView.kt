package com.example.mobile1project.ids.exfinal.views

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobile1project.ids.exfinal.maps.IntegratedMap
import com.example.mobile1project.ids.exfinal.models.Restaurant

@Composable
fun RestaurantDetailView(
    restaurant: Restaurant,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val colors = listOf(Color(0xFF6200EE), Color(0xFF03DAC6))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Header con botón de retroceso
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, "Back", tint = colors[0])
            }
            Text("Back", color = colors[0], modifier = Modifier.padding(start = 8.dp))
        }

        // Imagen destacada
        Image(
            painter = painterResource(id = getDrawableId(restaurant.imgName)),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentScale = ContentScale.Crop
        )

        // Nombre y rating
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = restaurant.rating.toString())
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mapa
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Log.d("RestaurantDetailScreen", "Lat: ${restaurant.latitude}, Lng: ${restaurant.longitude}")

                IntegratedMap(
                    latitude = restaurant.latitude,
                    longitude = restaurant.longitude,
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.LightGray) // <- Para confirmar que el mapa se intenta renderizar
                )

                FloatingActionButton(
                    onClick = { /* Acción para centrar mapa */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    containerColor = colors[0]
                ) {
                    Icon(Icons.Default.MyLocation, "Centrar")
                }
            }
        }

        // Información
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            InfoRow(Icons.Default.Schedule, "Horario: ${restaurant.schedule}")
            InfoRow(Icons.Default.LocalShipping, "Tiempo de envío: ${restaurant.delivery}")
            InfoRow(Icons.Default.AttachMoney, "Costo de envío: ${restaurant.fee}")
            InfoRow(Icons.Default.Phone, "Teléfono: ${restaurant.phone}")
        }

        // Botones inferiores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ExtendedFloatingActionButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${restaurant.phone}"))
                    context.startActivity(intent)
                },
                icon = { Icon(Icons.Default.Call, "Llamar") },
                text = { Text("Llamar") },
                containerColor = colors[0],
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            ExtendedFloatingActionButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.webSite))
                    context.startActivity(intent)
                },
                icon = { Icon(Icons.Default.Public, "Sitio web") },
                text = { Text("Sitio web") },
                containerColor = colors[1],
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(12.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}
