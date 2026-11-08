package com.example.myapplication.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
            val paddingPx = (16 * resources.displayMetrics.density).toInt()
            v.setPadding(systemBars.left + paddingPx, systemBars.top + paddingPx,
                systemBars.right + paddingPx, systemBars.bottom + paddingPx
            )
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

        if (mainVM.isRegistredPokemon( pokemonName )) {
            binding.imgFavorite.setImageResource(R.drawable.favorite_full)
            binding.imgFavorite.setColorFilter(getColor(R.color.red_100))
        }

        mainVM.requestPokemon(pokemonName)


    }

    fun setObserver() {
        mainVM.getPokemon().observe(this) { pokemon ->
            pokemon?.let {
                binding.textPokemonName.text = it.name.replaceFirstChar { it.uppercase() }

                val type1Name = it.types.first().type.name.replaceFirstChar { it.uppercase() }
                val type2Name = it.types.last().type.name.replaceFirstChar { it.uppercase() }

                binding.textType1.text = type1Name
                binding.textType1.setBackgroundColor(ContextCompat.getColor(this, getTypeColor(type1Name)))

                if (type1Name == type2Name) {
                    binding.textType2.visibility = View.GONE

                } else {
                    binding.textType2.visibility = View.VISIBLE
                    binding.textType2.text = type2Name
                    binding.textType2.setBackgroundColor(ContextCompat.getColor(this, getTypeColor(type2Name)))
                }

                // Define os stats
                binding.textPs.text = it.stats.listIterator(0).next().baseStat.toString()
                binding.textAtk.text = it.stats.listIterator(1).next().baseStat.toString()
                binding.textDef.text = it.stats.listIterator(2).next().baseStat.toString()
                binding.textSpatk.text = it.stats.listIterator(3).next().baseStat.toString()
                binding.textSpdef.text = it.stats.listIterator(4).next().baseStat.toString()
                binding.textSp.text = it.stats.listIterator(5).next().baseStat.toString()

                // Carrega a imagem
                Glide.with(this)
                    .load(it.sprites.other?.officialArtwork?.frontDefault)
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
        binding.imgFavorite.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.img_back -> finish()
            R.id.img_favorite -> {
                mainVM.addPokemon()
                binding.imgFavorite.setImageResource(R.drawable.favorite_full)
                Toast.makeText(this, "Pokémon salvo nos favoritos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Função auxiliar que mapeia a string do tipo para o seu arquivo de cor xml
    private fun getTypeColor(type: String): Int {
        return when (type.lowercase()) {
            "bug" -> R.color.type_bug
            "dark" -> R.color.type_dark
            "dragon" -> R.color.type_dragon
            "electric" -> R.color.type_electric
            "fairy" -> R.color.type_fairy
            "fighting" -> R.color.type_fighting
            "fire" -> R.color.type_fire
            "flying" -> R.color.type_flying
            "ghost" -> R.color.type_ghost
            "grass" -> R.color.type_grass
            "ground" -> R.color.type_ground
            "ice" -> R.color.type_ice
            "normal" -> R.color.type_normal
            "poison" -> R.color.type_poison
            "psychic" -> R.color.type_psychic
            "rock" -> R.color.type_rock
            "steel" -> R.color.type_steel
            "water" -> R.color.type_water
            else -> R.color.black
        }
    }
}