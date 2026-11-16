package com.example.myapplication.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.data.model.ProductModel
import com.example.myapplication.view.adapter.ListProductAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ListProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBinding()
        testBD()
    }

    fun setBinding() {
        binding.recyclerListProducts.layoutManager = GridLayoutManager(this, 1)
        binding.recyclerListProducts.adapter = adapter
    }

    fun setObserver() {

    }

    fun setListener() {

    }

    fun testBD() {
        val db = AppDatabase.getDatabase(this).ProductDAO()

        for (i in 1..5) {
            val prod = ProductModel().apply {
                this.name = "iPhone ${i + 12}"
                this.price = 100f * i * 25
                this.amount = i * 2
                this.isAvailable = true
            }

            var p: ProductModel = db.getByName(prod.name)
            if (p == null) db.insert(prod)
        }

        val productList = db.getAvailables(1)
        adapter.updateProdList(productList)

        if (productList.isNotEmpty()) {
            Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Derrota", Toast.LENGTH_SHORT).show()
        }

    }
}