package com.asadi.lace.view


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.VolleyError
import com.asadi.lace.contracts.DetailContract
import com.asadi.lace.databinding.ActivityDetailBinding
import com.asadi.lace.model.DetailModel
import com.asadi.lace.presenter.DetailPresenter
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView


class DetailActivity : AppCompatActivity(), DetailContract.View {


    private lateinit var bind: ActivityDetailBinding
    private lateinit var presenter: DetailContract.Presenter
    private lateinit var model: DetailContract.Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //creates  object new DetailModel
        model = DetailModel(applicationContext)
        //create object new DetailPresenter
        presenter = DetailPresenter(this, model)

        presenter.loadData(intent.getIntExtra("id", -1))
    }

    override fun onStop() {

        bind.slider.stopAutoCycle()
        super.onStop()
    }

    override fun setTitle(title: String) {
        bind.tvDetailTitle.text = title
    }

    override fun setPrice(price: String) {
        bind.tvDetailPrice.text = price
    }

    override fun setDescription(description: String) {
        bind.tvDetailDescription.text = description
    }

    override fun setSlider(urls: List<String>) {
        for (name in urls) {
            val textSliderView = TextSliderView(this)
            // initialize a SliderLayout
            textSliderView
                    //load url into slider image
                .image(name)
                    //image style
                .scaleType = BaseSliderView.ScaleType.Fit

            //set image into slider
            bind.slider.addSlider(textSliderView)
        }
        bind.slider.setPresetTransformer(SliderLayout.Transformer.Accordion)
        bind.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        bind.slider.setCustomAnimation(DescriptionAnimation())
        bind.slider.setDuration(4000)

    }

    override fun showError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }
}





