package com.asadi.lace.view

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.asadi.lace.R
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.dataClasses.Product
import com.asadi.lace.databinding.RecyclerItemBinding
import com.bumptech.glide.Glide


class ProductViewHolder(
    private val bind: RecyclerItemBinding
) : RecyclerView.ViewHolder(bind.root),
    HomeContract.ProductViewHolder {





    override fun setData(product: Product) {


     bind.tvTitle.text = product.title
        bind.tvBrand.text = product.brand
        Glide
            .with(bind.root)
            .load(product.thumbnail)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_100)
            .placeholder(R.drawable.ic_baseline_placeholder_100)
            .into(bind.ivThumbnail)


        bind.root.setOnClickListener {
            val intent = Intent(bind.root.context, DetailActivity::class.java)
            intent.putExtra("id", product.id)
            bind.root.context.startActivity(intent)
        }

    }


}
