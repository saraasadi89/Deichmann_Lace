package com.asadi.lace.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.asadi.lace.contracts.DetailContract
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.dataClasses.Product
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class DetailModel (
    private val context: Context
        ): DetailContract.Model {


    override fun setOnProductReceived(id:Int,onProductReceived: DetailContract.Model.OnProductReceived) {


        val queue = Volley.newRequestQueue(context)
        val url = "https://dummyjson.com/products/$id"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
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




