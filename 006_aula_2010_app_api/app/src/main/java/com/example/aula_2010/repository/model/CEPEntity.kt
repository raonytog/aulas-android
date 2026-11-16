package com.example.estudo.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CEPEntity(
    @SerializedName("cep")
    val cep: String = "",

    @SerializedName("logradouro")
    val logradouro: String = "",

    @SerializedName("complemento")
    val complemento: String = "",

    @SerializedName("unidade")
    val unidade: String = "",

    @SerializedName("bairro")
    val bairro: String = "",

    @SerializedName("localidade")
    val localidade: String = "",

    @SerializedName("uf")
    val uf: String = "",

    @SerializedName("estado")
    val estado: String = "",

    @SerializedName("regiao")
    val regiao: String = "",

    @SerializedName("ibge")
    val ibge: String = "",

    @SerializedName("gia")
    val gia: String = "",

    @SerializedName("ddd")
    val ddd: String = "",

    @SerializedName("siafi")
    val siafi: String = ""
) : Serializable