package com.example.brandstore.Fragments.HomeFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandstore.Model.ProductData
import com.example.brandstore.R
import java.util.ArrayList

//class AdapterHome(val context: Context, private val productList: ArrayList<ProductData>) :
//    RecyclerView.Adapter<AdapterHome.Holder>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
//        return Holder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return productList.size
//    }
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bind(productList[position], context)
//    }
//
//    inner class Holder (view: View?): RecyclerView.ViewHolder(view!!) {
//
//        val imageView = view?.findViewById<ImageView>(R.id.img)
//        val nameProduct = view?.findViewById<TextView>(R.id.name_product)
//        val priceProduct = view?.findViewById<TextView>(R.id.price)
//
//        fun bind(productData: ProductData, context: Context) {
//            nameProduct?.text = productData.nameProduct
//            priceProduct?.text = productData.priceProduct
//        }
//
//    }
//
//}