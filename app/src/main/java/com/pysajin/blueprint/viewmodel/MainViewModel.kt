package com.pysajin.blueprint.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pysajin.blueprint.R
import com.pysajin.blueprint.adapter.ItemAdapter
import com.pysajin.blueprint.model.ItemList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainViewModel(application: Application) : BaseViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    val itemAdapter = ItemAdapter(R.layout.item_list, this)
    var itemList = MutableLiveData<ArrayList<ItemList>>()

    init {
        itemList.value = itemListBase
        getItemList()
    }

    fun gotoMapPage(position: Int){
        Log.e("TEST","ERTEST")
        goMapPage()
    }

    fun getItem(position: Int): ItemList {
        return itemList.value!![position]
    }

    fun getItemList() {
        compositeDisposable.add(
            repository.getAllUserItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        itemList.value = it
                        itemAdapter.notifyDataSetChanged()
                    },
                    {
                        Log.e("getItemError :", it.toString())
                    }
                )
        )
    }

    private fun insertItem(item: ItemList) {
        compositeDisposable.add(
            repository.insertUserItem(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        itemList.value!!.add(item)
                        itemAdapter.notifyDataSetChanged()
                    },
                    {
                        Log.e("insertItemError :", it.toString())
                    }
                )
        )
    }

    private fun deleteItem(id: Int) {
        compositeDisposable.add(
            repository.deleteUserItem(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { },
                    {
                        Log.e("deleteItemError :", it.toString())
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}