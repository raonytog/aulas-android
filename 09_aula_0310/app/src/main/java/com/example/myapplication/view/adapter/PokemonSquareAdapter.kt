package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.repository.entity.PokemonResultEntity
import com.example.myapplication.databinding.PokemonSquareBinding
import com.example.myapplication.view.viewHolder.PokemonSquareViewHolder

class PokemonSquareAdapter(private val onItemClick: (PokemonResultEntity) -> Unit = {}): RecyclerView.Adapter<PokemonSquareViewHolder>() {
    private var pokemonList: List<PokemonResultEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonSquareViewHolder {
        val item = PokemonSquareBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonSquareViewHolder(item)
    }

    override fun onBindViewHolder(holder: PokemonSquareViewHolder, position: Int) {
        holder.bindVH(pokemonList[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return pokemonList.count()
    }

    fun updateList(list: List<PokemonResultEntity>) {
        pokemonList = list
        notifyDataSetChanged()
    }
}