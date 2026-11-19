package com.example.myapplication.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.data.model.PokemonModel
import com.example.myapplication.data.model.entityToModel
import com.example.myapplication.data.repository.client.ClientPokemonRetrofit
import com.example.myapplication.data.repository.entity.PokemonEntity
import com.example.myapplication.data.repository.entity.PokemonListResponse
import com.example.myapplication.data.repository.entity.PokemonResultEntity
import com.example.myapplication.data.repository.service.PokemonService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainViewModel(application: Application): AndroidViewModel(application) {
    var qtd_lista: Int = 20
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val favoritePokemonList = MutableLiveData<List<PokemonModel>>(emptyList())
    private var favoritesListener: ListenerRegistration? = null

    private val qtdAcertados = MutableLiveData<Int>(0)
    fun getQtdAcertados(): LiveData<Int> { return qtdAcertados }
    fun incAcertos() { qtdAcertados.value = (qtdAcertados.value ?: 0) + 1 }

    private val qtdErrados = MutableLiveData<Int>(0)
    fun getQtdErrados(): LiveData<Int> { return qtdErrados }
    fun incErros() { qtdErrados.value = (qtdErrados.value ?: 0) + 1 }


    private val errorMensage = MutableLiveData<String>()
    fun getErrorMensage(): LiveData<String> { return errorMensage }

    private val pokemonList = MutableLiveData<List<PokemonResultEntity>>()
    fun getPokemonList(): LiveData<List<PokemonResultEntity>> { return pokemonList }

    private val pokemon = MutableLiveData<PokemonEntity>()
    fun getPokemon(): LiveData<PokemonEntity> { return pokemon }

    fun getFavoritePokemonList(): LiveData<List<PokemonModel>> {
        return favoritePokemonList
    }

    private fun getUserPokemonCollection(): CollectionReference? {
        val uid = auth.currentUser?.uid ?: return null
        return db.collection("users").document(uid).collection("pokemon")
    }

    init {
        auth.addAuthStateListener {
            observeFavorites()
        }
    }

    private fun observeFavorites() {
        favoritesListener?.remove()

        val userPokemonCollection = getUserPokemonCollection()
        if (userPokemonCollection == null) {
            favoritePokemonList.value = emptyList()
            return
        }

        favoritesListener = userPokemonCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                errorMensage.value = "Erro ao carregar favoritos: ${error.localizedMessage}"
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val list = snapshot.toObjects(PokemonModel::class.java)
                favoritePokemonList.value = list
            }
        }
    }

    fun registraUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
            if (it.isSuccessful) { callback(true, null) }
            else { callback(false, it.exception?.localizedMessage) }
        }
    }

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.localizedMessage)
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
    }


    fun addPokemon() {
        val currentPokemon = pokemon.value ?: return

        if (isRegisteredPokemon(currentPokemon.name)) {
            errorMensage.value = getApplication<Application>().getString(R.string.toast_error_already_favorite)
            return
        }

        val userPokemonCollection = getUserPokemonCollection()
        if (userPokemonCollection == null) {
            errorMensage.value = "Usuário não autenticado."
            return
        }

        val pkmModel = entityToModel(currentPokemon)
        userPokemonCollection
            .document(pkmModel.name.lowercase())
            .set(pkmModel)
            .addOnFailureListener { e ->
                errorMensage.value = e.localizedMessage
            }
    }

    fun isRegisteredPokemon(name: String): Boolean {
        return favoritePokemonList.value?.any { it.name.equals(name, ignoreCase = true) } == true
    }

    fun deletePokemon(pokemon: PokemonModel) {
        val userPokemonCollection = getUserPokemonCollection()
        if (userPokemonCollection == null) {
            errorMensage.value = "Usuário não autenticado."
            return
        }

        userPokemonCollection
            .document(pokemon.name.lowercase())
            .delete()
            .addOnFailureListener { e ->
                errorMensage.value = e.localizedMessage
            }
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

    fun checkPokemonExists(pokemonName: String, callback: (Boolean) -> Unit) {
        val api = ClientPokemonRetrofit.createService(PokemonService::class.java)
        val request: Call<PokemonEntity> = api.getPokemonFromName(pokemonName.lowercase().trim())

        request.enqueue(object : Callback<PokemonEntity> {
            override fun onResponse(call: Call<PokemonEntity>, response: Response<PokemonEntity>) {
                callback(response.isSuccessful && response.body() != null)
            }

            override fun onFailure(call: Call<PokemonEntity>, t: Throwable) {
                callback(false)
            }
        })
    }
}