package com.eltec.myapplication.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eltec.myapplication.DetailActivity
import com.eltec.myapplication.R
import com.eltec.myapplication.databinding.ItemImgBinding
import com.squareup.picasso.Picasso

class ItemAdapter(
    private val context: Context,
    private val itemList: List<Item>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_img, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Item = itemList[position]

        val isPrivate = item.private
        holder.bind(item.imagen)

        holder.itemView.setOnClickListener {
            if (isPrivate == "0"){
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("p", item.programa)
                }
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Es privado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemImgBinding.bind(view)

        fun bind(img:String){
            Picasso.get().load(img).into(binding.itemImg)
        }

    }
}