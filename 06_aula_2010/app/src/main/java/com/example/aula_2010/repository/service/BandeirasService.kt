package com.example.aula_2010.repository.service

import com.example.aula_2010.repository.model.BandeiraEntity
import retrofit2.Call
import retrofit2.http.GET

interface BandeirasService {
    @GET("bandeiras-dos-estados")
    fun getBandeira(): Call<List<BandeiraEntity>>
}