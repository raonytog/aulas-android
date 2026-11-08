package com.example.myapplication.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.model.PokemonModel
import com.example.myapplication.databinding.PokemonLineBinding

class PokemonLineViewHolder (private val binding: PokemonLineBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindVH(pokemon: PokemonModel, onDeleteClick: (PokemonModel) -> Unit) {
        binding.textPokemonName.text = pokemon.name

        Glide.with( binding.root )
            .load(pokemon.sprite)
            .into(binding.imgPokemon)

        binding.imgFavorite.setOnClickListener {
            onDeleteClick(pokemon)
        }
    }
}