package com.pysajin.blueprint.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pysajin.blueprint.BR
import com.pysajin.blueprint.R
import com.pysajin.blueprint.databinding.ActivityMapBinding
import com.pysajin.blueprint.viewmodel.MapViewModel

class MapActivity : BaseActivity<ActivityMapBinding>(R.layout.activity_map) {
    private lateinit var viewModel :MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        binding.setVariable(BR.mapVM, viewModel)

        this.viewModel.infoActivity.observe(this, Observer {
            changeActivity(it)
        })

    }
}
