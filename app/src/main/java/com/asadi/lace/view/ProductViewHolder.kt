package com.asadi.lace.view

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asadi.lace.R
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.dataClasses.Product
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class ProductViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view),
    HomeContract.ProductViewHolder {

    private val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    private val tvBrand = view.findViewById<TextView>(R.id.tv_brand)
    private val ivThumbnail = view.findViewById<ImageView>(R.id.iv_thumbnail)

    override fun setData(product: Product) {

        tvTitle.text = product.title
        tvBrand.text = product.brand
        Glide
            .with(view.context)
            .load(product.thumbnail)
            .centerCrop()
            //.placeholder(R.id)
            .into(ivThumbnail);
        view.setOnClickListener {
            val intent = Intent(view.context, DetailActivity::class.java)
            intent.putExtra("id", product.id)
            view.context.startActivity(intent)
        }

    }


}
