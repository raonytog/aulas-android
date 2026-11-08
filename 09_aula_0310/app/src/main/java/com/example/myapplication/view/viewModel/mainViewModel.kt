package com.example.myapplication.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.data.model.PokemonModel
import com.example.myapplication.data.repository.client.ClientPokemonRetrofit
import com.example.myapplication.data.repository.entity.PokemonEntity
import com.example.myapplication.data.repository.entity.PokemonListResponse
import com.example.myapplication.data.repository.entity.PokemonResultEntity
import com.example.myapplication.data.repository.service.PokemonService
import com.example.myapplication.data.room.AppDatabase

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainViewModel(application: Application): AndroidViewModel(application) {
    var qtd_lista: Int = 20
    private val db = AppDatabase.getDatabase(application.applicationContext).AnimalDAO()

    private val errorMensage = MutableLiveData<String>()
    fun getErrorMensage(): LiveData<String> { return errorMensage }

    private val pokemonList = MutableLiveData<List<PokemonResultEntity>>()
    fun getPokemonList(): LiveData<List<PokemonResultEntity>> { return pokemonList }

    private val pokemon = MutableLiveData<PokemonEntity>()
    fun getPokemon(): LiveData<PokemonEntity> { return pokemon }

    fun getFavoritePokemonList(): LiveData<List<PokemonModel>> { return db.getAll() }

    fun addPokemon() {
        val currentPokemon = pokemon.value ?: return

        if (isRegistredPokemon(currentPokemon.name) != null) {
            errorMensage.value = getApplication<Application>().getString(R.string.toast_error_already_favorite)
            return
        }

        val typeStr = currentPokemon.types.firstOrNull()?.type?.name ?: ""
        val sprite = currentPokemon.sprites.other.officialArtwork.frontDefault ?: ""
        val hp = currentPokemon.stats.getOrNull(0)?.baseStat ?: 0
        val attack = currentPokemon.stats.getOrNull(1)?.baseStat ?: 0
        val defense = currentPokemon.stats.getOrNull(2)?.baseStat ?: 0
        val spAtk = currentPokemon.stats.getOrNull(3)?.baseStat ?: 0
        val spDef = currentPokemon.stats.getOrNull(4)?.baseStat ?: 0
        val speed = currentPokemon.stats.getOrNull(5)?.baseStat ?: 0

        val pkm = PokemonModel(
            name = currentPokemon.name.replaceFirstChar { it.uppercase() },
            sprite = sprite,
            type = typeStr,
            hp = hp,
            attack = attack,
            defense = defense,
            spAtk = spAtk,
            spDef = spDef,
            speed = speed
        )
        db.insert(pkm)
    }

    fun isRegistredPokemon( name: String): Boolean {
        val p = db.getByName( name )
        return p != null
    }

    fun deletePokemon( pokemon: PokemonModel) {
        db.delete(pokemon)
    }

    fun requestList() {
        val api = ClientPokemonRetrofit.createService(PokemonService::class.java)
        val request: Call<PokemonListResponse> = api.getAllPokemons(this.qtd_lista)

        request.enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    pokemonList.value = response.body()?.results
                } else {
                    errorMensage.value = getApplication<Application>().getString(R.string.toast_error_not_found_pokemon)
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
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
