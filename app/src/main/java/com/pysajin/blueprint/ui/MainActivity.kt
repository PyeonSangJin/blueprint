package com.pysajin.blueprint.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pysajin.blueprint.BR
import com.pysajin.blueprint.R
import com.pysajin.blueprint.databinding.ActivityMainBinding
import com.pysajin.blueprint.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.setVariable(BR.mainVM, viewModel)
    }



}
