package com.example.myapplication.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.data.repository.client.ClientPokemonRetrofit
import com.example.myapplication.data.repository.entity.PokemonEntity
import com.example.myapplication.data.repository.service.PokemonService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainViewModel(application: Application): AndroidViewModel(application) {
    var qtd_lista: Int = 1

    private val errorMensage = MutableLiveData<String>()
    fun getErrorMensage(): LiveData<String> { return errorMensage }

    private val pokemonList = MutableLiveData<List<PokemonEntity>>()
    fun getPokemonList(): LiveData<List<PokemonEntity>> { return pokemonList }

    private val pokemon = MutableLiveData<PokemonEntity>()
    fun getPokemon(): LiveData<PokemonEntity> { return pokemon }

    fun requestList() {
        val api = ClientPokemonRetrofit.createService(PokemonService::class.java)
        val request: Call<List<PokemonEntity>> = api.getAllPokemons(this.qtd_lista)

        request.enqueue(object : Callback<List<PokemonEntity>> {
            override fun onResponse(call: Call<List<PokemonEntity>>, response: Response<List<PokemonEntity>>) {
                if (response.isSuccessful) {
                    pokemonList.value = response.body()
                } else {
                    errorMensage.value = getApplication<Application>().getString(R.string.toast_error_not_found_pokemon)
                }
            }

            override fun onFailure(call: Call<List<PokemonEntity>>, t: Throwable) {
                errorMensage.value = getApplication<Application>().getString(R.string.toast_error_not_found_pokemon)
            }
        })
    }

    fun requestPokemon(pokemonName: String) {
        val api = ClientPokemonRetrofit.createService(PokemonService::class.java)
        val request: Call<PokemonEntity> = api.getPokemonFromName(pokemonName.lowercase())

        request.enqueue(object : Callback<PokemonEntity> {
            override fun onResponse(call: Call<PokemonEntity>, response: Response<PokemonEntity>) {
                if (response.isSuccessful && response.body() != null) {
                    pokemon.value = response.body()
                } else {
                    errorMensage.value = getApplication<Application>().getString(R.string.toast_error_not_found_pokemon)
                }
            }

            override fun onFailure(call: Call<PokemonEntity>, t: Throwable) {
                errorMensage.value = getApplication<Application>().getString(R.string.toast_error_not_found_pokemon)
            }
        })
    }
}
