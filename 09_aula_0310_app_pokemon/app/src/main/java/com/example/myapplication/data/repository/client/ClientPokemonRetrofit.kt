package com.example.myapplication.data.repository.client

import com.example.myapplication.utils.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientPokemonRetrofit {
    companion object {
        private lateinit var INSTANCE: Retrofit

        private fun getClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(Constantes.API.POKEMON_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun <S> createService(className: Class<S>): S {
            return getClientInstance().create(className)
        }
    }
}