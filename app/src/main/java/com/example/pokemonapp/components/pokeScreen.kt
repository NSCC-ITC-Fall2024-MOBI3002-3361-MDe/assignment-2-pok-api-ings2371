package com.example.pokemonapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.R
import com.example.pokemonapp.models.PokeViewModel

@Composable
fun pokeScreen(pokeViewModel: PokeViewModel = viewModel()){

    Scaffold (
    topBar = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            // Center the content within the Box
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), //loads image resource
                contentDescription = "the pokemon logo", //description
                modifier = Modifier
                    .size(200.dp)
                    .padding(5.dp)
            )
        }
    },
        content = {paddingValues ->
            var pokeSearch by remember { mutableStateOf("") }
            val pokemon by pokeViewModel.pokemon.collectAsState()

            Row(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {

                TextField(
                    value = pokeSearch,
                    onValueChange = {pokeSearch = it},
                    singleLine = true,
                    modifier = Modifier.padding(8.dp)
                )
                Button(onClick = {pokeViewModel.fetchPokemon(pokeSearch)}) { Text("Go") }
            }
            Column (modifier = Modifier
                .padding(paddingValues)
                .padding(40.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                //checks if pokemon is not null, and if not let is executed
                pokemon?.let {
                    //spaces so it doesn't overlap a bit with row
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        //pokemon passes as it
                        model = it.sprites.front_default,
                        contentDescription = "${it.name} sprite",
                        modifier = Modifier.size(125.dp)
                    )


                    Text(it.name, color = Color.DarkGray, fontSize = 40.sp)
                    //joinToString extracting type names from a dataclass that's a list
                    //that's joined and separated by comas(that's default for it)
                    //type is an instance of Types and shows how each item in types is converted
                    Text("Types: ${it.types.joinToString { type -> type.type.name }}", fontSize = 22.sp)
                    //heights for each pokemon are messed up on example,
                    //api uses meters x 10 while the EX says ft using the meters x 10
                    Text("Height: ${it.height}ft", color = Color.DarkGray, modifier = Modifier.padding(10.dp))
                    //in api weight is kg x 10 while demo uses lbs with kg x 10 results
                    Text("Weight: ${it.weight}lbs", color = Color.DarkGray)
                }
            }
        }


    )


}


