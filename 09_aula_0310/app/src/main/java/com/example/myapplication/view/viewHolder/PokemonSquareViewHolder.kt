package com.example.myapplication.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.repository.entity.PokemonResultEntity
import com.example.myapplication.databinding.PokemonSquareBinding

class PokemonSquareViewHolder (private val binding: PokemonSquareBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindVH(pokemon: PokemonResultEntity, onItemClick: (PokemonResultEntity) -> Unit = {}) {
        binding.textPokemonName.text = pokemon.name

        Glide.with( binding.root )
            .load(pokemon.spriteUrl)
            .into(binding.imgPokemon)

        binding.root.setOnClickListener {
            onItemClick(pokemon)
        }
    }
}