package com.asadi.lace.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asadi.lace.R
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.view.ProductViewHolder

class HomeAdapter(
    private val presenter: HomeContract.Presenter

) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.recycler_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // TODO holder.setData(product[position])
        presenter.onBindViewHolder(holder, position)

    }

    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }


}