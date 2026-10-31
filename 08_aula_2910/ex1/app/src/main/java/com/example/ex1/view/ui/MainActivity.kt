package com.example.ex1.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ex1.R
import com.example.ex1.data.model.AnimalModel
import com.example.ex1.data.room.AppDatabase
import com.example.ex1.databinding.ActivityMainBinding
import com.example.ex1.utils.Constantes
import com.example.ex1.view.adapter.ListAnimalAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ListAnimalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBinding()
        setObserver()
        setListener()
        setupBD()
    }

    fun setBinding() {
        binding.recyclerListAnimal.layoutManager = LinearLayoutManager(this)
        binding.recyclerListAnimal.adapter = adapter
    }

    fun setObserver() {

    }

    fun setListener() {

    }

    fun setupBD() {
        val db = AppDatabase.getDatabase(this).AnimalDAO()

        for (animal in Constantes.MOCK_DATA.ANIMAL_LIST) {
            val animal = AnimalModel().apply {
                this.name = animal
            }

            var aux = db.getByName(animal.name)
            if (aux == null) db.insert(animal)
        }

        val productList = db.getAll()
        adapter.updateProdList(productList)

        var toast_length = Toast.LENGTH_SHORT
        if (productList.isNotEmpty()) { Toast.makeText(this, "Sucesso", toast_length).show() }
        else { Toast.makeText(this, "Derrota", toast_length).show()  }
    }
}