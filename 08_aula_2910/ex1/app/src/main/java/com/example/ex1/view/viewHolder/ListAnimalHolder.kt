package com.example.ex1.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.ex1.data.model.AnimalModel
import com.example.ex1.databinding.AnimalLineBinding

class ListAnimalHolder(private val binding: AnimalLineBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindVH(prod: AnimalModel){
        binding.textAnimalName.text = prod.name
        binding.textAnimalId.text = prod.id.toString()
    }
}