package com.example.ex1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ex1.data.model.AnimalModel
import com.example.ex1.databinding.AnimalLineBinding
import com.example.ex1.view.viewHolder.ListAnimalHolder

class ListAnimalAdapter : RecyclerView.Adapter<ListAnimalHolder>() {
    private var animalList: List<AnimalModel> = listOf() // a interface armazena os itens da lista

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAnimalHolder {
        val item = AnimalLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAnimalHolder(item)
    }

    override fun onBindViewHolder(holder: ListAnimalHolder, position: Int) {
        holder.bindVH(animalList[position])
    }

    override fun getItemCount(): Int {
        return animalList.count()
    }

    fun updateProdList(list: List<AnimalModel>) {
        animalList = list
        notifyDataSetChanged()
    }
}