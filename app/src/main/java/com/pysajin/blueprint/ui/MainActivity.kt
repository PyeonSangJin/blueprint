package com.pysajin.blueprint.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pysajin.blueprint.BR
import com.pysajin.blueprint.R
import com.pysajin.blueprint.databinding.ActivityMainBinding
import com.pysajin.blueprint.fragment.MainFragment
import com.pysajin.blueprint.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    enum class FragmentPos(val pos:Int){MAIN(0), SETTING(1)}

    private val moveFragment = object {
        fun moveMainPage(){
            onFragmentChange(FragmentPos.MAIN)
        }
        fun moveSettingPage(){
            onFragmentChange(FragmentPos.SETTING)
        }
    }

    fun onFragmentChange(position: FragmentPos) {
        when (position) {
            FragmentPos.MAIN -> replaceFragment(MainFragment())
            FragmentPos.SETTING -> replaceFragment(MainFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager.popBackStack()
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment).commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.setVariable(BR.mainVM, viewModel)

        fragmentManager = supportFragmentManager
        transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, MainFragment()).addToBackStack("MAIN").commit()

        viewModel.getItemList()

        this.viewModel.infoActivity.observe(this, Observer {
            changeActivity(it)
        })

        this.viewModel.itemList.observe(this, Observer {
            viewModel.getItemList()
        })
    }



}
