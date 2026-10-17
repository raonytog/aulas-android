package com.example.aula_2909

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val sp = Preferences(application, application.applicationContext)

    private var _textRandom = MutableLiveData<String>()
    var textRandom: LiveData<String> = _textRandom

    private val _textName = MutableLiveData<String>()
    val textName: LiveData<String> = _textName

    private val _selectedFood = MutableLiveData<String>()
    var selectedFood: LiveData<String> = _selectedFood

    private val _navigateBack = MutableLiveData<Boolean>()
    var navigateBack: LiveData<Boolean> = _navigateBack

    private val _navigateJump = MutableLiveData<Boolean>()
    var navigateJump: LiveData<Boolean> = _navigateJump

    fun setup_1() {
        _textName.value = sp.getString("name")
        if (_textName.value != "") _navigateJump.value = true
        else _navigateJump.value = false
    }

    fun validateName(name: String) {
        val duration = Toast.LENGTH_SHORT
        var toast: Toast

        if (name.isEmpty()) {
            toast = Toast.makeText(application.applicationContext, R.string.nome_vazio, duration)
        }

        else if (name.length < 3) {
            toast = Toast.makeText(application.applicationContext, R.string.palavra_pequena, duration)
        }

        else {
            sp.setString("name", name)
            toast = Toast.makeText(application.applicationContext, R.string.movendo, duration)
            _navigateJump.value = true
        }
        toast.show()

    }

    fun setup_2() {
        _textName.value = sp.getString("name")
    }

    fun selectFood(food: String) {
        _selectedFood.value = food

        when (_selectedFood.value) {
            "apple" -> { _textRandom.value = CuriosidadesAlimentares.getAboutApple() }
            "pizza" -> { _textRandom.value = CuriosidadesAlimentares.getAboutPizza() }
            "icecream" -> { _textRandom.value = CuriosidadesAlimentares.getAboutIcecream() }
        }
    }

    fun generateRandomMensage() {
        _textRandom.value = CuriosidadesAlimentares.getAllRandom()
    }

    fun logout() {
        sp.setString("name", "")
        sp.setString("food", "")
        _navigateBack.value = true
        _selectedFood.value = ""
    }

    fun removeFromLists(phrase: String) {
        CuriosidadesAlimentares.delFromAll( phrase ).toString()
        _textRandom.value = CuriosidadesAlimentares.getAllRandom()
    }
}