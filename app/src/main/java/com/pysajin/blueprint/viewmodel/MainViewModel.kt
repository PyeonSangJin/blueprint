package com.pysajin.blueprint.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import com.pysajin.blueprint.R
import com.pysajin.blueprint.adapter.ItemAdapter
import com.pysajin.blueprint.interfaces.ItemTouchHelperCallback
import com.pysajin.blueprint.model.ItemList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : BaseViewModel(application) {
    private val compositeDisposable = CompositeDisposable()

    val itemAdapter = ItemAdapter(R.layout.item_list, this)
    val helper: ItemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(itemAdapter, application.applicationContext))
    var itemList = MutableLiveData<Array<ItemList>>()

    init {

        itemList.value = itemListBase
        getItemList()

        itemList.value = arrayOf(
            ItemList(1, "1", "333"),
            ItemList(1, "2", "333"),
            ItemList(1, "3", "333"),
            ItemList(1, "4", "333"),
            ItemList(1, "5", "333"),
            ItemList(1, "6", "333"),
            ItemList(1, "7", "333"),
            ItemList(1, "8", "333"),
            ItemList(1, "9", "333"),
            ItemList(1, "10", "333")
        )
    }

    fun gotoMapPage(position: Int) {
        Log.e("TEST", "MainViewModel의 gotoMapPage")
        val selcetItem = itemList.value!![position]
        goMapPage()
    }

    fun getItem(position: Int): ItemList {
        return itemList.value!![position]
    }

    fun getItemList() {
//        테스트를 위한 주석
//        compositeDisposable.add(
//            repository.getAllUserItem()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        itemList.value = it
//                        itemAdapter.notifyDataSetChanged()
//                    },
//                    {
//                        Log.e("getItemError :", it.toString())
//                    }
//                )
//        )
    }

    private fun insertItem(item: ItemList) {
        compositeDisposable.add(
            repository.insertUserItem(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        getItemList()
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