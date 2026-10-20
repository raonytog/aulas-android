package com.example.estudo.repository.model

import androidx.navigationevent.NavigationEventInfo
import com.google.gson.annotations.SerializedName
class CEPEntity {
    @SerializedName("cep")
    val cep: String = ""

    @SerializedName("logradouro")
    val logradouro: String = ""

    @SerializedName("complemento")
    val complemento: String = ""

    @SerializedName("unidade")
    val unidade: String = ""

    @SerializedName("bairro")
    val bairro: String = ""

    @SerializedName("localidade")
    val localidade: String = ""

    @SerializedName("uf")
    val uf: String = ""

    @SerializedName("estado")
    val estado: String = ""

    @SerializedName("complemento")
    val regiao: String = ""

    @SerializedName("complemento")
    val ibge: String = ""

    @SerializedName("complemento")
    val gia: String = ""

    @SerializedName("complemento")
    val ddd: String = ""

    @SerializedName("complemento")
    val siafi: String = ""
}