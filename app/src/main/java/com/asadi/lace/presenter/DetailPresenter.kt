package com.asadi.lace.presenter

import com.android.volley.VolleyError
import com.asadi.lace.contracts.DetailContract
import com.asadi.lace.dataClasses.Product

class DetailPresenter(
    //DetailPresenter constructor parameters
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
        view.setSlider(data.images)

    }

    override fun loadData(id:Int) {
        //calls model method to load data
        model.setOnProductReceived(id,object :DetailContract.Model.OnProductReceived{
            override fun onSuccess(product: Product) {
                //save data in presenter
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

