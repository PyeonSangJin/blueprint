package com.pysajin.blueprint.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.pysajin.blueprint.R
import com.pysajin.blueprint.databinding.ActivityMainBinding
import com.pysajin.blueprint.viewmodel.MainViewModel

class MapRegistActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_map_regist) {
    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

}