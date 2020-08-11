package com.pysajin.blueprint

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pysajin.blueprint.adapter.ItemAdapter

@BindingAdapter("android:setItemAdapter")
fun RecyclerView.gg(adapter: RecyclerView.Adapter<ItemAdapter.Holder>) {
    this.setHasFixedSize(true)
    val mLayoutManager = LinearLayoutManager(this.context)
    mLayoutManager.reverseLayout = true;
    mLayoutManager.stackFromEnd = true;
    this.layoutManager = mLayoutManager
    this.adapter = adapter
}