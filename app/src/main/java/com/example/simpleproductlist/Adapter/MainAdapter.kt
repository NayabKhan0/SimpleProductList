package com.example.simpleproductlist.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleproductlist.Model.Product
import com.example.simpleproductlist.Model.ProductList
import com.example.simpleproductlist.R

class MainAdapter(private val context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    var productList = mutableListOf<Product>()
    fun setList(product: ProductList?) {
        if (product != null) {
            productList = product.products.toMutableList()
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = productList[position]
        if (item.thumbnail.isNotEmpty()) {
            Glide.with(context)
                .load(item.thumbnail)
                .into(holder.productImage)
        } else {
            holder.productImage.setImageResource(R.drawable.no_image)
        }

        holder.productName.text = item.title
        holder.productPrice.text = item.price.toString()
        holder.brandName.text = item.brand

    }

    override fun getItemCount(): Int = productList.size



    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val brandName: TextView = itemView.findViewById(R.id.brandName)
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
    }
}