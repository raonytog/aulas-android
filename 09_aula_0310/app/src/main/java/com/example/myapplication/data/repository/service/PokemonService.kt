package com.example.myapplication.data.repository.service

import com.example.myapplication.data.repository.entity.PokemonEntity
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.GET

interface PokemonService {
    // https://pokeapi.co/api/v2/pokemon/spiritomb
    @GET("{name}")
    fun getPokemonFromName(@Path("name") name: String): Call<PokemonEntity>

    // https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20
    @GET("?limit={qtd]")
    fun getAllPokemons(@Path("qtd") qtd: Int): Call<List<PokemonEntity>>
}