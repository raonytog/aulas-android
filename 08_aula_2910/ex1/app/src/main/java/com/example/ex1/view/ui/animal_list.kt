package com.example.ex1.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ex1.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ex1.data.room.AppDatabase
import com.example.ex1.databinding.ActivityAnimalListBinding
import com.example.ex1.view.adapter.ListAnimalAdapter
import com.example.ex1.viewModel.MainViewModel

class animal_list : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAnimalListBinding
    private lateinit var mainVM: MainViewModel

    private val adapter = ListAnimalAdapter { animal ->
        val db = AppDatabase.getDatabase(this).AnimalDAO()
        db.delete(animal)
        Toast.makeText(this, "Animal (${animal.name}) deletado!", Toast.LENGTH_SHORT).show()
        loadAnimals()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnimalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this)[MainViewModel::class.java]

        setBinding()
        setObserver()
        loadAnimals()
    }

    fun setObserver() {
        mainVM.getIsOnListPage().observe(this, Observer {
            if (it) return@Observer
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

        mainVM.getToastError().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    fun setBinding() {
        binding.recyclerListAnimal.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerListAnimal.adapter = adapter
        binding.imgAdd.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        loadAnimals()
    }

    private fun loadAnimals() {
        val db = AppDatabase.getDatabase(this).AnimalDAO()
        val list = db.getAll()
        adapter.updateProdList(list)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_add -> { mainVM.setIsOnListPage(false) }
        }
    }
}