package com.asadi.lace.presenter

import com.android.volley.VolleyError
import com.asadi.lace.contracts.DetailContract
import com.asadi.lace.dataClasses.Product

class DetailPresenter(
    private val view:DetailContract.View,
    private val model:DetailContract.Model
) :DetailContract.Presenter{

lateinit var data:Product
    override fun showTitleData() {
       view.setTitle(data.title)
    }

    override fun showPriceData() {
        view.setPrice(data.price.toString())
    }

    override fun showDescriptionData() {
        view.setDescription(data.description)
    }

    override fun showSliderData() {
        val url_maps = HashMap<String, String>()
       for(i in data.images)
           url_maps[i]=i
        view.setSlider(url_maps)

    }

    override fun loadData(id:Int) {
        model.setOnProductReceived(id,object :DetailContract.Model.OnProductReceived{
            override fun onSuccess(product: Product) {
                data=product
                showTitleData()
                showPriceData()
                showDescriptionData()
                showSliderData()
            }

            override fun onFailure(error: VolleyError) {
               view.showError(error)
            }

        })
    }


}

