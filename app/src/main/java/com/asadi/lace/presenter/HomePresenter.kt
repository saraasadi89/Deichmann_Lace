package com.asadi.lace.presenter

import com.android.volley.VolleyError
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.dataClasses.Product
import com.asadi.lace.view.ProductViewHolder

class HomePresenter(
    private val view: HomeContract.View,
    private val model: HomeContract.Model

) : HomeContract.Presenter {

    lateinit var data:List<Product>

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setData(data[position])

    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun loadRecyclerView() {
        model.setOnProductsReceived(object :HomeContract.Model.OnProductsReceived{
            override fun onSuccess(products: List<Product>) {
                data=products
                view.initRecycler()

            }

            override fun onFailure(error: VolleyError) {
              view.toastError(error)
            }


        })
    }
}