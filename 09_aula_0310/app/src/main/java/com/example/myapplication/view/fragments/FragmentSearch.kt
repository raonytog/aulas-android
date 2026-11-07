package com.example.myapplication.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.view.ui.ResultScreen

class FragmentSearch : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
