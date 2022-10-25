package com.asadi.lace.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.asadi.lace.R
import com.asadi.lace.adapters.HomeAdapter
import com.asadi.lace.contracts.HomeContract
import com.asadi.lace.model.HomeModel
import com.asadi.lace.presenter.HomePresenter

class HomeActivity : AppCompatActivity(), HomeContract.View {

    lateinit var recycler: RecyclerView
    lateinit var model: HomeContract.Model
    lateinit var presenter: HomeContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        model = HomeModel(applicationContext)
        presenter = HomePresenter(this, model)
        recycler = findViewById(R.id.recyclerView)

        presenter.loadRecyclerView()
    }

    override fun initRecycler() {

        recycler.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        recycler.adapter = HomeAdapter(presenter)
    }

    override fun toastError(error: VolleyError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }


}