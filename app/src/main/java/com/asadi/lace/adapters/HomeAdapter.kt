package com.asadi.lace.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.databinding.RecyclerItemBinding
import com.asadi.lace.view.ProductViewHolder

class HomeAdapter(
    //HomeAdapter Constructor Parameter
    //Get by Interface (Dependency inversion)
    private val presenter: HomeContract.Presenter

) : RecyclerView.Adapter<ProductViewHolder>() {

    //create ProductViewHolder
    override fun onCreateViewHolder(
        //parent -> recyclerview
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            RecyclerItemBinding
                .inflate(
                    LayoutInflater
                        .from(parent.context),
                    parent,
                    false
                )
        )
    }

    //set viewHolder to Recycler
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        presenter.onBindViewHolder(holder, position)

    }


    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }


}