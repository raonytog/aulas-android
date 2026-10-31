package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.ProductModel
import com.example.myapplication.databinding.ProductLineBinding
import com.example.myapplication.view.viewHolder.ListProductViewHolder

class ListProductAdapter : RecyclerView.Adapter<ListProductViewHolder>() {

    private var prodList: List<ProductModel> = listOf() // a interface armazena os itens da lista

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        val item = ProductLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ListProductViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        holder.bindVH(prodList[position])
    }

    override fun getItemCount(): Int {
        return prodList.count()
    }

    fun updateProdList(list: List<ProductModel>) {
        prodList = list
        notifyDataSetChanged()
    }
}
