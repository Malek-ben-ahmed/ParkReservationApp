package com.example.parkreservationapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.material3.CardDefaults
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp





@Composable
fun Slots() {
    var slots by remember { mutableStateOf(listOf<Slot>()) }
    val context = LocalContext.current

    // Fonction pour charger les slots depuis le serveur
    fun loadSlots() {
        RetrofitClient.instance.getSlots().enqueue(object : Callback<List<Slot>> {
            override fun onResponse(call: Call<List<Slot>>, response: Response<List<Slot>>) {
                if (response.isSuccessful) {
                    slots = response.body() ?: emptyList()
                } else {
                    Toast.makeText(context, "Erreur lors du rafraîchissement", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Slot>>, t: Throwable) {
                Toast.makeText(context, "Erreur : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Charger les slots au lancement
    LaunchedEffect(Unit) { loadSlots() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(top=50.dp, end = 20.dp, start = 20.dp, bottom = 30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(slots) { slot ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .clickable {
                        Toast.makeText(context,"Slot ${slot.num}",Toast.LENGTH_SHORT).show()
                    },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Slot ${slot.num}", Modifier.padding(start = 6.dp, top = 2.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = "car",
                        tint = if (slot.etat == "disponible") Color.Green else Color.Red,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }

    Button(
        onClick = { loadSlots() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        colors = ButtonDefaults.buttonColors(Color(0xFF0078B8))
    ) {
        Text(text = "Rafraîchir")
    }
}



@Composable
fun bottombar() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigationBar(
            windowInsets = NavigationBarDefaults.windowInsets,
            modifier = Modifier.align(Alignment.BottomCenter),
            containerColor = Color.White,
        ) {

            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "home",
                        tint = Color(0xFF0078B8)
                    )
                },
                label = { Text(text = "Home") }
            )
            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Event,
                        contentDescription = "Réservation",
                        tint = Color(0xFF0078B8)
                    )
                },
                label = { Text(text = "Réservation") }
            )

            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.CreditCard,
                        contentDescription = "Paiement",
                        tint = Color(0xFF0078B8)
                    )
                },
                label = { Text(text = "Paiement") }
            )

        }
    }
}


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column() {
                navbar(title="les slots disponibles")
                Slots()
                bottombar()


            }}
    }
}

