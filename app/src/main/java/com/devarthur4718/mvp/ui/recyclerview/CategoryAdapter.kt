package com.devarthur4718.mvp.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.devarthur4718.mvp.R
import com.devarthur4718.mvp.databinding.ItemCategoryProductBinding
import com.devarthur4718.mvp.extension.CircularProgress
import com.devarthur4718.mvp.glide.GlideApp
import com.devarthur4718.mvp.repository.database.entity.ProductCategory
import com.google.firebase.storage.FirebaseStorage
import java.lang.Exception

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : ProductCategory = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding : ItemCategoryProductBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ProductCategory){
                binding.tvICategoryName.text = item.category
                val storage = FirebaseStorage.getInstance()

                try{
                    //Apply storage image into image view
                    if(!item.imgurl.isNullOrEmpty()){
                        val gsReferencePhoto = storage.getReferenceFromUrl(item.imgurl)
                        GlideApp.with(itemView.context)
                            .asDrawable()
                            .load(gsReferencePhoto)
                            .placeholder(itemView.context?.CircularProgress())
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .into(binding.ivCategoryThumb)
                    }

                }catch (e:Exception){
                       //Failed to load image for some reason.
                        binding.ivCategoryThumb.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_image_black_24dp))
                }

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