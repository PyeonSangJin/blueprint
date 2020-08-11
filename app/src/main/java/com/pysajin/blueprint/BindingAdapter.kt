package com.pysajin.blueprint

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pysajin.blueprint.adapter.ItemAdapter

@BindingAdapter("android:setItemAdapter")
fun RecyclerView.gg(adapter: RecyclerView.Adapter<ItemAdapter.Holder>) {
    this.setHasFixedSize(true)
    val mLayoutManager = LinearLayoutManager(this.context)
    mLayoutManager.reverseLayout = true
    mLayoutManager.stackFromEnd = true
    this.layoutManager = mLayoutManager
    this.adapter = adapter
}

@BindingAdapter("android:setHelper")
fun RecyclerView.hh(helper: ItemTouchHelper) {
    helper.attachToRecyclerView(this)
}