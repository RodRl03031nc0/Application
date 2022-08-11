package com.eltec.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eltec.myapplication.databinding.ItemImgBinding
import com.eltec.myapplication.model.ItemsP
import com.squareup.picasso.Picasso

class ItemAdapter(
    private val context: Context,
    private val itemList: List<ItemsP>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_img, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: ItemsP = itemList.get(position)

        val isPrivate = item.private
        Picasso.get().load(item.imagen).into(holder.img())

        holder.itemView.setOnClickListener {
            if (isPrivate == "0"){

            } else {
                Toast.makeText(context, "Es privado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemImgBinding.bind(view)

        fun img(){
            Picasso.get().load("").into(binding.itemImg)
        }

        init {
            itemView.setOnClickListener (this)
        }

         fun onClick(view: View) {
             val pos = adapterPosition
             if (pos != RecyclerView.NO_POSITION){

             }
         }

    }
}