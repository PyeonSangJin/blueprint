package com.pysajin.blueprint.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pysajin.blueprint.BR
import com.pysajin.blueprint.R
import com.pysajin.blueprint.interfaces.IFragmentChangeListener
import com.pysajin.blueprint.viewmodel.MainViewModel
import com.pysajin.blueprint.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private lateinit var viewModel: MainViewModel

    companion object {
        @JvmStatic
        fun newInstance(movePageListener: IFragmentChangeListener): MainFragment {
            return MainFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[(MainViewModel::class.java)]
        binding.setVariable(BR.mainVM, viewModel)
    }
}