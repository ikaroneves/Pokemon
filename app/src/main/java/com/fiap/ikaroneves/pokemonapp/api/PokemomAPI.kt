package com.fiap.ikaroneves.pokemonapp.api

import com.fiap.ikaroneves.pokemonapp.model.Pokemom
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface PokemomAPI {
    @GET( "/api/v2/pokemon/{id}")
    fun getPokemom(@Path( "id") idPokemom : Int) : Call<Pokemom>
}