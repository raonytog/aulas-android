package com.example.myapplication.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.entityToModel
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.utils.Constantes
import com.example.myapplication.view.ui.ResultScreen
import com.example.myapplication.view.viewModel.mainViewModel

class FragmentSearch : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainVM: mainViewModel

    private var key = Constantes.POKEMON.LIST.random()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setup() {
        mainVM.getPokemon().observe(viewLifecycleOwner) { pokemon ->
            pokemon?.let { entity ->
                val pkmModel = entityToModel(entity)
                Glide.with(this)
                    .load(pkmModel.sprite)
                    .into(binding.imgPikachu)
            }
        }

        mainVM.getQtdAcertados().observe(viewLifecycleOwner) { correct ->
            binding.textCorrect.text = correct.toString()
            val wrong = mainVM.getQtdErrados().value ?: 0
            binding.textTotal.text = (correct + wrong).toString()
        }

        mainVM.getQtdErrados().observe(viewLifecycleOwner) { wrong ->
            binding.textWrong.text = wrong.toString()
            val correct = mainVM.getQtdAcertados().value ?: 0
            binding.textTotal.text = (correct + wrong).toString()
        }
    }

    private fun loadRandomPokemon() {
        key = Constantes.POKEMON.LIST.random()
        mainVM.requestPokemon(key)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainVM = ViewModelProvider(requireActivity())[mainViewModel::class.java]
        setup()

        val duration = Toast.LENGTH_SHORT
        binding.buttonFindPokemon.setOnClickListener {
            val pokemonName = binding.editPokemonName.text.toString().trim()

            if (pokemonName.isEmpty()) { // se o nome estiver vazio
                Toast.makeText(requireContext(), getText(R.string.toast_error_empty_pokemon_name), duration).show()
                return@setOnClickListener
            }

            mainVM.checkPokemonExists(pokemonName) { exists ->
                if (isAdded) {
                    if (exists) { // se existe o pokemon
                        val intent = Intent(requireContext(), ResultScreen::class.java).apply {
                            putExtra("PokemonName", pokemonName)
                        }

                        if (pokemonName == key) {
                            Toast.makeText(requireContext(), getText(R.string.toast_found_pokemon), duration).show()
                            mainVM.incAcertos()
                        }

                        else {
                            Toast.makeText(requireContext(), getText(R.string.toast_another_found_pokemon), duration).show()
                            mainVM.incErros()
                        }

                        startActivity(intent)

                    } else { // se nao existe
                        Toast.makeText(requireContext(), getText(R.string.toast_error_not_found_pokemon), duration).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadRandomPokemon()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
