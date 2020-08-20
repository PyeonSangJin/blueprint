package com.pysajin.blueprint.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pysajin.blueprint.BR
import com.pysajin.blueprint.R
import com.pysajin.blueprint.databinding.ActivityMainBinding
import com.pysajin.blueprint.databinding.ActivityMapRegistBinding
import com.pysajin.blueprint.fragment.MainFragment
import com.pysajin.blueprint.viewmodel.MainViewModel
import com.pysajin.blueprint.viewmodel.MapRegistViewModel

class MapRegistActivity : BaseActivity<ActivityMapRegistBinding>(R.layout.activity_map_regist) {
    private lateinit var viewModel: MapRegistViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MapRegistViewModel::class.java)
        binding.setVariable(BR.mapRegistVM, viewModel)

        this.viewModel.infoActivity.observe(this, Observer {
            Log.e("Test","MainActivity의 observer setting부분 $it")
            changeActivity(it, data = this.viewModel.selectedItem)
        })
    }

}