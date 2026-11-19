package com.example.myapplication.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.model.PokemonModel
import com.example.myapplication.databinding.FragmentFavoriteBinding
import com.example.myapplication.view.adapter.PokemonLineAdapter
import com.example.myapplication.view.ui.ResultScreen
import com.example.myapplication.view.viewModel.mainViewModel

class FragmentFavorite : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainVM: mainViewModel
    private lateinit var adapter: PokemonLineAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVM = ViewModelProvider(requireActivity())[mainViewModel::class.java]
        adapter = PokemonLineAdapter(
            onDeleteClick = { pokemonToDelete ->
                showDeleteDialog(pokemonToDelete)
            },
            onItemClick = { pokemonClicked ->
                val intent = Intent(requireContext(), ResultScreen::class.java).apply {
                    putExtra("PokemonName", pokemonClicked.name)
                }
                startActivity(intent)
            }
        )

        binding.recycleFavoriteList.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleFavoriteList.adapter = adapter

        mainVM.getFavoritePokemonList().observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }
    }

    override fun onResume() {
        super.onResume()
        mainVM.getFavoritePokemonList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDeleteDialog(pokemon: PokemonModel) {
        val formattedName = pokemon.name.replaceFirstChar { it.uppercase() }
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_delete_title))
            .setMessage(getString(R.string.dialog_delete_message, formattedName))
            .setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
                mainVM.deletePokemon(pokemon)
            }
            .setNegativeButton(getString(R.string.dialog_no), null)
            .create()
            .show()
    }
}