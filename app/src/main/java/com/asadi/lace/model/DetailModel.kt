package com.asadi.lace.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.asadi.lace.contracts.DetailContract
import com.asadi.lace.dataClasses.Product
import com.google.gson.Gson
import org.json.JSONObject

class DetailModel (
    private val context: Context
        ): DetailContract.Model {


    override fun setOnProductReceived(id:Int,onProductReceived: DetailContract.Model.OnProductReceived) {

        //create new request line
        val queue = Volley.newRequestQueue(context)
        val url = "https://dummyjson.com/products/$id"

        // Request a string
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            //gets string request and converts into product
            { response ->
                val obj = JSONObject(response).toString()
                val gson = Gson()
                val product = gson.fromJson(obj, Product::class.java)
                onProductReceived.onSuccess(product)


            },
            {
                onProductReceived.onFailure(it)
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
}




