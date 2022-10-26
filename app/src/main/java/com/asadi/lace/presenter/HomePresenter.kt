package com.asadi.lace.presenter

import com.android.volley.VolleyError
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.dataClasses.Product
import com.asadi.lace.view.ProductViewHolder

class HomePresenter(
    //Dependency Injection
    //by Interface
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

    //Gets all Products
    override fun loadRecyclerView() {
        //calls function from model to get Products
        model.setOnProductsReceived(object :HomeContract.Model.OnProductsReceived{
            //Implements onSuccess
            override fun onSuccess(products: List<Product>) {
                data=products
                view.initRecycler()

            }
            //Implements onFailure
            override fun onFailure(error: VolleyError) {
              view.toastError(error)
            }


        })
    }
}