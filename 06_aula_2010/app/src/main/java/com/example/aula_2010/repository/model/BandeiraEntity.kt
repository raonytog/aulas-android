package com.example.aula_2010.repository.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class BandeiraEntity(
    @SerializedName("uf")
    val uf: String = "",

    @SerializedName("flag_url")
    val flagUrl: String = ""
) : java.io.Serializable