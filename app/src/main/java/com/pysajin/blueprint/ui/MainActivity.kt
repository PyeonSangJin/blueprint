package com.pysajin.blueprint.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pysajin.blueprint.BR
import com.pysajin.blueprint.R
import com.pysajin.blueprint.databinding.ActivityMainBinding
import com.pysajin.blueprint.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.setVariable(BR.mainVM, viewModel)

        viewModel.getItemList()

        this.viewModel.infoActivity.observe(this, Observer {
            changeActivity(it)
        })

        this.viewModel.itemList.observe(this, Observer {
            viewModel.getItemList()
        })
    }



}
