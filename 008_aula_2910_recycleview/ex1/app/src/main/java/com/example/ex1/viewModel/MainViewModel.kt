package com.example.ex1.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ex1.data.model.AnimalModel
import com.example.ex1.data.room.AppDatabase
import com.example.ex1.view.adapter.ListAnimalAdapter

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application.applicationContext).AnimalDAO()
    private val adapter = ListAnimalAdapter()

    private val isOnlistPage = MutableLiveData<Boolean>()
    fun getIsOnListPage(): LiveData<Boolean> { return isOnlistPage }
    fun setIsOnListPage(value: Boolean) { isOnlistPage.value = value }

    private val toastError = MutableLiveData<String>()
    fun getToastError(): LiveData<String> { return toastError }

    fun addAnimal(name: String) {
        if (name.isBlank()) return

        setIsOnListPage(true)

        val animal = AnimalModel().apply { this.name = name }
        val aux = db.getByName(animal.name)
        if (aux == null) {
            db.insert(animal)
        }
    }
}