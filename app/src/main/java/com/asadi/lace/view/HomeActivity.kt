package com.asadi.lace.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.asadi.lace.adapters.HomeAdapter
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.databinding.ActivityHomeBinding
import com.asadi.lace.model.HomeModel
import com.asadi.lace.presenter.HomePresenter

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var bind: ActivityHomeBinding
    private lateinit var model: HomeContract.Model
    private lateinit var presenter: HomeContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //Construct with inversion
        model = HomeModel(applicationContext)
        presenter = HomePresenter(this, model)


        //Starting Point
        presenter.loadRecyclerView()
    }

    override fun initRecycler() {

        bind.recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        //sets new HomeAdapter to RecyclerAdapter
        bind.recyclerView.adapter = HomeAdapter(presenter)
    }

    override fun toastError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }


}