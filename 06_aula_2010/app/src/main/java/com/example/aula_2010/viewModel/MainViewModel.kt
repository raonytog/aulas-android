package com.example.estudo.viewModel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.aula_2010.repository.service.CEPService
import com.example.estudo.repository.client.ClientRetrofit
import com.example.estudo.repository.model.CEPEntity
import com.example.estudo.utils.Constantes


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val cepResult = MutableLiveData<CEPEntity>()
    fun getCepResult(): LiveData<CEPEntity> { return cepResult }

    private val errorMensage = MutableLiveData<String>()
    fun getErrorMensage(): LiveData<String> { return errorMensage }

    fun requestCep(cep: String) {
        val apiBlogService = ClientRetrofit.createService(CEPService::class.java)
        val blogPost: Call<CEPEntity> = apiBlogService.getInfoFromCEP(cep)

        blogPost.enqueue(object : Callback<CEPEntity> {
            override fun onResponse(call: Call<CEPEntity>, response: Response<CEPEntity>) {
                cepResult.value = response.body()
            }

            override fun onFailure(call: Call<CEPEntity>, t: Throwable) {
                errorMensage.value = "CEP não existe!"
            }
        })
    }


}