package com.pysajin.blueprint.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.pysajin.blueprint.viewmodel.MainViewModel
import androidx.recyclerview.widget.RecyclerView
import com.pysajin.blueprint.databinding.ItemListBinding

class ItemAdapter(val layoutId: Int, val mMainViewModel: MainViewModel) : RecyclerView.Adapter<ItemAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemListBinding>(layoutInflater, viewType, parent, false)
        return Holder(binding)
    }

    override fun getItemCount() =
        if (mMainViewModel.itemList.value != null)
            mMainViewModel.itemList.value!!.size
        else 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mMainViewModel, position)
    }

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition()

    private fun getLayoutIdForPosition(): Int = layoutId

    class Holder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.recentSearchText) {
        fun bind(mMainViewModel: MainViewModel, position: Int) {
            binding.mainVM = mMainViewModel
            binding.position = position
            binding.executePendingBindings() // 즉시 바인딩
        }
    }
}