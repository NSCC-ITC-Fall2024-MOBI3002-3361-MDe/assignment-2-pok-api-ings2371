package com.example.pokemonapp.models

data class Pokemon(
    val sprites: Sprites,
    val name: String,
    val height: Number,
    val weight: Number,
    val types: ArrayList<Types>
)

data class Sprites(
    val front_default:String
)

data class Types(
    val type: TypeDetail
)

data class TypeDetail(
    val name: String
)
