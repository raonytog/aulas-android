package com.example.aula_2010.repository.service

import com.example.estudo.repository.model.CEPEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CEPService {
    @GET("{cep}/json")
    fun getInfoFromCEP(@Path("cep") cep: String): Call<CEPEntity>

}