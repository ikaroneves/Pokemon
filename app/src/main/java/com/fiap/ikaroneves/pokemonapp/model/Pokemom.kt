package com.fiap.ikaroneves.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class Pokemom (
    @SerializedName("name") val nome:String,
    @SerializedName("sprites") val sprites: Sprites
)
