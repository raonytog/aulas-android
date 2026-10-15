package com.example.aula_2909

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var textRandomTxt = MutableLiveData<String>()

    fun textRandomTxt(): MutableLiveData<String> {
        return textRandomTxt
    }

    init {
        textRandomTxt.value = "Registro de Produto"
    }

}