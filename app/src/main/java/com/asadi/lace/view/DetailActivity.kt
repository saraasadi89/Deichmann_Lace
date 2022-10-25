package com.asadi.lace.view


import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.VolleyError
import com.asadi.lace.R
import com.asadi.lace.contracts.DetailContract
import com.asadi.lace.model.DetailModel
import com.asadi.lace.presenter.DetailPresenter
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView


class DetailActivity : AppCompatActivity(), DetailContract.View {
    lateinit var imSlider: SliderLayout
    lateinit var tvTitle: TextView
    lateinit var tvPrice: TextView
    lateinit var tvDescription: TextView
    lateinit var presenter:DetailContract.Presenter
    lateinit var model: DetailContract.Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Toast.makeText(this, intent.getIntExtra("id", -1).toString(), Toast.LENGTH_SHORT).show()

        tvTitle = findViewById(R.id.tv_detail_title)
        tvPrice = findViewById(R.id.tv_detail_price)
        tvDescription = findViewById(R.id.id_detail_description)
        imSlider = findViewById(R.id.slider)
         model=DetailModel(applicationContext)
        presenter=DetailPresenter(this,model)
        presenter.loadData(intent.getIntExtra("id", -1))
    }

    override fun onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        imSlider.stopAutoCycle()
        super.onStop()
    }

    override fun setTitle(title: String) {
        tvTitle.text = title
    }

    override fun setPrice(price: String) {
        tvPrice.text = price
    }

    override fun setDescription(description: String) {
        tvDescription.text = description
    }

    override fun setSlider(urls: HashMap<String, String>) {
        for (name in urls.keys) {
            val textSliderView = TextSliderView(this)
            // initialize a SliderLayout
            textSliderView
                .description(name)
                .image(urls[name]!!)
                .setScaleType(BaseSliderView.ScaleType.Fit)

            imSlider.addSlider(textSliderView)
        }
        imSlider.setPresetTransformer(SliderLayout.Transformer.Accordion)
        imSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        imSlider.setCustomAnimation(DescriptionAnimation())
        imSlider.setDuration(4000)

    }

    override fun showError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }
}





