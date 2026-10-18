package com.example.aula_2909

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application
import com.example.app_comidas.utils.Preferences

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val sp = Preferences(application, application.applicationContext)

    private var textRandom = MutableLiveData<String>()
    private val textName = MutableLiveData<String>()
    private val selectedFood = MutableLiveData<String>()
    private val navigateBack = MutableLiveData<Boolean>()
    private val navigateJump = MutableLiveData<Boolean>()

    fun getTextRandom(): LiveData<String> {
        return textRandom
    }

    fun getTextName(): LiveData<String> {
        return textName
    }

    fun getSelectedFood(): LiveData<String> {
        return selectedFood
    }

    fun getNavigateBack(): LiveData<Boolean> {
        return navigateBack
    }

    fun getNavigateJump(): LiveData<Boolean> {
        return navigateJump
    }

    fun setup_1() {
        textName.value = sp.getString("name")
        if (textName.value != "") navigateJump.value = true
        else navigateJump.value = false
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
            navigateJump.value = true
        }
        toast.show()

    }

    fun setup_2() {
        textName.value = sp.getString("name")
    }

    fun selectFood(food: String) {
        selectedFood.value = food

        when (selectedFood.value) {
            //"apple" -> { textRandom.value = CuriosidadesAlimentares.getAboutApple() }
            //"pizza" -> { textRandom.value = CuriosidadesAlimentares.getAboutPizza() }
            //"icecream" -> { textRandom.value = CuriosidadesAlimentares.getAboutIcecream() }
        }
    }

    fun generateRandomMensage() {
        // textRandom.value = CuriosidadesAlimentares.getAllRandom()
    }

    fun logout() {
        sp.setString("name", "")
        sp.setString("food", "")
        navigateBack.value = true
        selectedFood.value = ""
    }

    fun removeFromLists(phrase: String) {
        //CuriosidadesAlimentares.delFromAll( phrase ).toString()
        //textRandom.value = CuriosidadesAlimentares.getAllRandom()
    }
}