package com.asadi.lace.contracts

import android.content.Context
import android.icu.text.CaseMap
import com.android.volley.VolleyError
import com.asadi.lace.dataClasses.Product

interface DetailContract {

    interface View {

        fun setTitle(title:String)
        fun setPrice(price :String)
        fun setDescription(description:String)
        fun setSlider(urls:HashMap<String,String>)
        fun showError(error: VolleyError)

    }

    interface Model {

        fun setOnProductReceived(id: Int, onProductReceived: OnProductReceived)

        interface OnProductReceived {
            fun onSuccess(product: Product)
            fun onFailure(error: VolleyError)
        }
    }

    interface Presenter {

        fun showTitleData()
        fun showPriceData()
        fun showDescriptionData()
        fun showSliderData()

        fun loadData(id:Int)
    }
}