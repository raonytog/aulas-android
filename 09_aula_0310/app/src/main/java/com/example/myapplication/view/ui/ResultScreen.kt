package com.example.myapplication.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityResultScreenBinding
import com.example.myapplication.view.viewModel.mainViewModel

class ResultScreen : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResultScreenBinding
    private lateinit var mainVM: mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainVM = ViewModelProvider(this).get(mainViewModel::class.java)

        setup()
        setListener()
        setObserver()
    }

    fun setup() {
        val pokemonName = intent.getStringExtra("PokemonName") ?: "pikachu"
        binding.textPokemonName.text = pokemonName

        mainVM.requestPokemon( pokemonName )
    }

    fun setObserver() {
        mainVM.getPokemon().observe(this) { pokemon ->
            pokemon?.let {
                binding.textPokemonName.text = it.name

                // define os tipos
                if (it.types.first().type.name == it.types.last().type.name) {
                    binding.textType1.text = it.types.first().type.name
                } else {
                    binding.textType1.text = it.types.first().type.name
                    binding.textType2.text = it.types.last().type.name
                }

                binding.textPs.text = it.stats.listIterator(0).next().baseStat.toString()
                binding.textAtk.text = it.stats.listIterator(1).next().baseStat.toString()
                binding.textDef.text = it.stats.listIterator(2).next().baseStat.toString()
                binding.textSpatk.text = it.stats.listIterator(3).next().baseStat.toString()
                binding.textSpdef.text = it.stats.listIterator(4).next().baseStat.toString()
                binding.textSp.text = it.stats.listIterator(5).next().baseStat.toString()

                Glide.with(this)
                    .load(it.sprites.other.home.frontDefault)
                    .into(binding.imgPokemon)
            }
        }

        mainVM.getErrorMensage().observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setListener() {
        binding.imgBack.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.img_back -> finish()
        }
    }
}
