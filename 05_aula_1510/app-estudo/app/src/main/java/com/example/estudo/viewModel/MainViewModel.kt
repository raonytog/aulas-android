package com.example.estudo.viewModel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.estudo.repository.client.ClientRetrofit
import com.example.estudo.repository.model.DeezerResponseEntity
import com.example.estudo.repository.service.TrackTittleService
import com.example.estudo.utils.Preferences

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var sp = Preferences(application, application.applicationContext)

    private var user = MutableLiveData<String>()
    fun getUser(): LiveData<String> { return user }

    private var password = MutableLiveData<String>()
    fun getPassword(): LiveData<String> { return password }

    private var tittleText = MutableLiveData<String>()
    fun getTittleText(): LiveData<String> { return tittleText }

    private var showTittle = MutableLiveData<Boolean>()
    fun getShowTittle(): LiveData<Boolean> { return showTittle }

    private var login = MutableLiveData<Boolean>()
    fun getLogin(): LiveData<Boolean> { return login }

    private var backLogin = MutableLiveData<Boolean>()
    fun getBackLogin(): LiveData<Boolean> { return backLogin }

    private var skipLogin = MutableLiveData<Boolean>()
    fun getSkipLogin(): LiveData<Boolean> { return skipLogin }

    private var backSearch = MutableLiveData<Boolean>()
    fun getBackSearch(): LiveData<Boolean> { return backSearch }

    fun main_setup() {
        skipLogin.value = sp.getString("name").isNotEmpty() && sp.getString("password").isNotEmpty()
    }

    fun search_setup() {

    }

    fun song_setup() {
        tittleText.value = sp.getString("tittle")
    }

    fun backSearch() {
        backSearch.value = true
    }

    fun backLogin() {
        sp.setString("name", "")
        sp.setString("password", "")
        backLogin.value = true
    }

    fun validateLogin(user: String, password: String) {
        this.user.value = user
        this.password.value = password
        this.login.value = getPassword().value == "admin123" && getUser().value?.isNotEmpty() == true && getUser().value?.length!! > 3

        if (this.login.value == true) {
            sp.setString("name", getUser().value.toString())
            sp.setString("password", getPassword().value.toString())
        }
    }

    fun requestArtist(artistName: String) {
        val apiService = ClientRetrofit.createService(TrackTittleService::class.java)
        val foodCall: Call<DeezerResponseEntity> = apiService.getTrackTittle(artistName)

        foodCall.enqueue(object : Callback<DeezerResponseEntity> {
            override fun onResponse(call: Call<DeezerResponseEntity>, response: Response<DeezerResponseEntity>) {
                val list = response.body()?.data

                if (!list.isNullOrEmpty()) {
                    val item = list.random()
                    tittleText.value = "${item.title} "
                    showTittle.value = true
                    sp.setString("tittle", tittleText.value.toString())

                } else {
                    tittleText.value = "Nenhuma música encontraada encontrada"
                    showTittle.value = false
                }
            }

            override fun onFailure(call: Call<DeezerResponseEntity>, t: Throwable) {
                tittleText.value = "Falha na requisição da API"
            }
        })
    }
}