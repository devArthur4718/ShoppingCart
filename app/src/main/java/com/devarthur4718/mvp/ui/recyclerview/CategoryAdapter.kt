package com.devarthur4718.mvp.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devarthur4718.mvp.databinding.ItemCategoryProductBinding
import com.devarthur4718.mvp.repository.database.entity.ProductCategory

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var data = listOf<ProductCategory>()
        set(value){
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val item : ProductCategory = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding : ItemCategoryProductBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ProductCategory){
                binding.tvICategoryName.text = item.category
        }
        companion object {
            fun from(parent : ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCategoryProductBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}