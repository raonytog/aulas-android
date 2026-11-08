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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setup() {
        mainVM.requestPokemon(Constantes.POKEMON.LIST.random())
        mainVM.getPokemon().observe(viewLifecycleOwner) { pokemon ->
            pokemon?.let { entity ->
                val pkmModel = entityToModel(entity)
                Glide.with(this)
                    .load(pkmModel.sprite)
                    .into(binding.imgPikachu)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainVM = ViewModelProvider(this).get(mainViewModel::class.java)
        setup()

        val duration = Toast.LENGTH_SHORT
        binding.buttonFindPokemon.setOnClickListener {
            val pokemonName = binding.editPokemonName.text.toString()
            if (pokemonName.isNotEmpty()) {
                val intent = Intent(requireContext(), ResultScreen::class.java).apply {
                    putExtra("PokemonName", pokemonName)
                }
                Toast.makeText(requireContext(), getText(R.string.toast_found_pokemon), duration).show()
                startActivity(intent)

            } else {
                Toast.makeText(requireContext(), getText(R.string.toast_error_not_found_pokemon),duration).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
