package com.example.estudo.repository.model

import com.google.gson.annotations.SerializedName

class DeezerResponseEntity {
    @SerializedName("data")
    val data: List<TrackTittleEntity> = listOf()
}