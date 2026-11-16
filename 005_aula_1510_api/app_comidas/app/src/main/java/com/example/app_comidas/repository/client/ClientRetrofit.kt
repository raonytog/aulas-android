package com.example.app_comidas.repository.client

import com.example.app_comidas.utils.Consts

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientRetrofit {
    companion object {
        private lateinit var INSTANCE: Retrofit

        private fun getClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(Consts.API.URL_BASE)
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