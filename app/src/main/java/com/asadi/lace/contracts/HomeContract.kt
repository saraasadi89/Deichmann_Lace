package com.asadi.lace.contracts


import com.android.volley.VolleyError
import com.asadi.lace.dataClasses.Product


interface HomeContract {

    interface  View{

        fun toastError(error: VolleyError)
        fun initRecycler()

    }
    interface Model{

        fun setOnProductsReceived(onProductsReceived:OnProductsReceived)

        interface OnProductsReceived{
            fun onSuccess(products:List<Product>)
            fun onFailure(error:VolleyError)
        }

    }
    interface Presenter{
        fun onBindViewHolder(holder: com.asadi.lace.view.ProductViewHolder, position: Int)
        fun getItemCount(): Int

        fun loadRecyclerView()


    }

    interface ProductViewHolder{
         fun setData(product: Product)


    }


}