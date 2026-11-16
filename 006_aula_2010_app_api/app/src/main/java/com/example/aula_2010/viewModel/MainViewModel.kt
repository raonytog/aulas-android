package com.example.estudo.viewModel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.application
import com.example.aula_2010.R
import com.example.aula_2010.repository.model.BandeiraEntity
import com.example.aula_2010.repository.service.BandeirasService

import com.example.aula_2010.repository.service.CEPService
import com.example.estudo.repository.client.ClientRetrofitBandeiras
import com.example.estudo.repository.client.ClientRetrofitCEP
import com.example.estudo.repository.model.CEPEntity


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val cepResult = MutableLiveData<CEPEntity>()
    fun getCepResult(): LiveData<CEPEntity> { return cepResult }

    private val errorMensage = MutableLiveData<String>()
    fun getErrorMensage(): LiveData<String> { return errorMensage }

    private val bandeira = MutableLiveData<String>()
    fun getBandeira() : LiveData<String> { return bandeira }

    fun requestCep(cep: String) {
        if (cep.length == 0) {
            errorMensage.value = getApplication<Application>().getString( R.string.toast_error_empity_cep )
            return
        }

        else if (cep.length < 8) {
            errorMensage.value = getApplication<Application>().getString( R.string.toast_error_less_thant_eight_digits )
            return
        }

        val apiCEPService = ClientRetrofitCEP.createService(CEPService::class.java)
        val cepCall: Call<CEPEntity> = apiCEPService.getInfoFromCEP(cep)
        cepCall.enqueue(object : Callback<CEPEntity> {
            override fun onResponse(call: Call<CEPEntity>, response: Response<CEPEntity>) {
                cepResult.value = response.body()
            }

            override fun onFailure(call: Call<CEPEntity>, t: Throwable) {
                errorMensage.value = getApplication<Application>().getString( R.string.toast_error_invalid_cep )
            }
        })
    }

    fun requestBandeira(uf: String) {
        val apiBandeirasService = ClientRetrofitBandeiras.createService(BandeirasService::class.java)
        val bandeiraRequest: Call<List<BandeiraEntity>> = apiBandeirasService.getBandeira()

        bandeiraRequest.enqueue(object : Callback<List<BandeiraEntity>> {
            override fun onResponse(call: Call<List<BandeiraEntity>>, response: Response<List<BandeiraEntity>>) {
                val lista = response.body()
                val bandeiraRequisitada = lista?.find {
                    it.uf.equals(uf, ignoreCase = true)
                }
                bandeira.value = bandeiraRequisitada?.flagUrl
            }

            override fun onFailure(call: Call<List<BandeiraEntity>>, t: Throwable) {
                errorMensage.value = getApplication<Application>().getString( R.string.toast_error_not_found_flag )
            }
        })
    }


}