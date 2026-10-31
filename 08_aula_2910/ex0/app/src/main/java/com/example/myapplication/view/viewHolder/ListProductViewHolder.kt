package com.example.myapplication.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.ProductModel
import com.example.myapplication.databinding.ProductLineBinding

class ListProductViewHolder(private val binding: ProductLineBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindVH(prod: ProductModel){
        binding.textProdNameAnswer.text = prod.name
        binding.textProdPriceAnswer.text = prod.price.toString()
        binding.textProdAmountAnswer.text = prod.amount.toString()
    }

}