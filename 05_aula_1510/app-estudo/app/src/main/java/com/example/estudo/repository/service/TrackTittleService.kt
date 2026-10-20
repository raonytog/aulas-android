package com.example.estudo.repository.service

import com.example.estudo.repository.model.DeezerResponseEntity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackTittleService {
    @GET("search")
    fun getTrackTittle(@Query("q") trackTittle: String): Call<DeezerResponseEntity>
}