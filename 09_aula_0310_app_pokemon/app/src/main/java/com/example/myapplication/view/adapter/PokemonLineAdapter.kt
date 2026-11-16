package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.PokemonModel
import com.example.myapplication.databinding.PokemonLineBinding
import com.example.myapplication.view.viewHolder.PokemonLineViewHolder

class PokemonLineAdapter(
    private val onDeleteClick: (PokemonModel) -> Unit = {},
    private val onItemClick: (PokemonModel) -> Unit = {}
) : RecyclerView.Adapter<PokemonLineViewHolder>() {
    private var pokemonList: List<PokemonModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonLineViewHolder {
        val item = PokemonLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonLineViewHolder(item)
    }

    override fun onBindViewHolder(holder: PokemonLineViewHolder, position: Int) {
        holder.bindVH(pokemonList[position], onDeleteClick, onItemClick)
    }

    override fun getItemCount(): Int {
        return pokemonList.count()
    }

    fun updateList(list: List<PokemonModel>) {
        pokemonList = list
        notifyDataSetChanged()
    }
}