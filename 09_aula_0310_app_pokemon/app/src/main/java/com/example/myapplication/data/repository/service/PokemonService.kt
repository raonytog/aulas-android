package com.example.myapplication.data.repository.service

import com.example.myapplication.data.repository.entity.PokemonEntity
import com.example.myapplication.data.repository.entity.PokemonListResponse
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    // https://pokeapi.co/api/v2/pokemon/spiritomb
    @GET("{name}")
    fun getPokemonFromName(@Path("name") name: String): Call<PokemonEntity>

    // https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20
    @GET(".")
    fun getAllPokemons(@Query("limit") qtd: Int): Call<PokemonListResponse>
}