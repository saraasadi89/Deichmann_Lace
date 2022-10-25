package com.asadi.lace.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.dataClasses.Product
import com.google.gson.GsonBuilder
import org.json.JSONObject

class HomeModel(
    private val context: Context
) : HomeContract.Model {



    override fun setOnProductsReceived(onProductsReceived: HomeContract.Model.OnProductsReceived) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://dummyjson.com/products"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val obj = JSONObject(response)
                val productsArray = obj.getJSONArray("products").toString()
                val gson = GsonBuilder().create()
                val productsList = gson.fromJson(productsArray, Array<Product>::class.java).toList()
                onProductsReceived.onSuccess(productsList)

            },
            {
                onProductsReceived.onFailure(it)
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

}